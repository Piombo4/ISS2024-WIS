### conda install diagrams
from diagrams import Cluster, Diagram, Edge
from diagrams.custom import Custom
import os
os.environ['PATH'] += os.pathsep + 'C:/Program Files/Graphviz/bin/'

graphattr = {     #https://www.graphviz.org/doc/info/attrs.html
    'fontsize': '22',
}

nodeattr = {   
    'fontsize': '22',
    'bgcolor': 'lightyellow'
}

eventedgeattr = {
    'color': 'red',
    'style': 'dotted'
}
evattr = {
    'color': 'darkgreen',
    'style': 'dotted'
}
with Diagram('sprint1_architettura_with_msgArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctx_wis', graph_attr=nodeattr):
          oprobot=Custom('oprobot','./qakicons/symActorSmall.png')
          wis=Custom('wis','./qakicons/symActorSmall.png')
          wastestorage=Custom('wastestorage','./qakicons/symActorSmall.png')
          ashstorage=Custom('ashstorage','./qakicons/symActorSmall.png')
          incinerator=Custom('incinerator','./qakicons/symActorSmall.png')
     with Cluster('ctx_gui', graph_attr=nodeattr):
          ssgui=Custom('ssgui','./qakicons/symActorSmall.png')
     with Cluster('ctx_monitoringdevice', graph_attr=nodeattr):
          led=Custom('led','./qakicons/symActorSmall.png')
          sonar=Custom('sonar','./qakicons/symActorSmall.png')
     with Cluster('ctx_basic_robot', graph_attr=nodeattr):
          basic_robot=Custom('basic_robot(ext)','./qakicons/externalQActor.png')
     oprobot >> Edge(color='magenta', style='solid', decorate='true', label='<doplan &nbsp; >',  fontcolor='magenta') >> basic_robot
     wis >> Edge(color='blue', style='solid',  decorate='true', label='<led_on &nbsp; led_off &nbsp; blink &nbsp; >',  fontcolor='blue') >> led
     oprobot >> Edge(color='blue', style='solid',  decorate='true', label='<ash_out &nbsp; >',  fontcolor='blue') >> ashstorage
     ashstorage >> Edge(color='blue', style='solid',  decorate='true', label='<ash_qty &nbsp; >',  fontcolor='blue') >> wis
     oprobot >> Edge(color='blue', style='solid',  decorate='true', label='<robot_status &nbsp; >',  fontcolor='blue') >> wis
     incinerator >> Edge(color='blue', style='solid',  decorate='true', label='<burn_out &nbsp; >',  fontcolor='blue') >> oprobot
     oprobot >> Edge(color='blue', style='solid',  decorate='true', label='<burn_in &nbsp; >',  fontcolor='blue') >> incinerator
     wis >> Edge(color='blue', style='solid',  decorate='true', label='<get_waste &nbsp; >',  fontcolor='blue') >> wastestorage
     wastestorage >> Edge(color='blue', style='solid',  decorate='true', label='<waste_qty &nbsp; >',  fontcolor='blue') >> wis
     wis >> Edge(color='blue', style='solid',  decorate='true', label='<start_robot &nbsp; >',  fontcolor='blue') >> oprobot
     wis >> Edge(color='blue', style='solid',  decorate='true', label='<update_gui &nbsp; >',  fontcolor='blue') >> ssgui
     incinerator >> Edge(color='blue', style='solid',  decorate='true', label='<burning &nbsp; burn_out &nbsp; >',  fontcolor='blue') >> wis
diag
