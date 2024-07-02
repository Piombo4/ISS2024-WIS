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
with Diagram('sprint0_architettura_with_msgArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctx_wis', graph_attr=nodeattr):
          wis=Custom('wis','./qakicons/symActorSmall.png')
     with Cluster('ctx_gui', graph_attr=nodeattr):
          ssgui=Custom('ssgui','./qakicons/symActorSmall.png')
     with Cluster('ctx_wastestorage', graph_attr=nodeattr):
          wastestorage=Custom('wastestorage','./qakicons/symActorSmall.png')
     with Cluster('ctx_ashstorage', graph_attr=nodeattr):
          ashstorage=Custom('ashstorage','./qakicons/symActorSmall.png')
     with Cluster('ctx_incinerator', graph_attr=nodeattr):
          incinerator=Custom('incinerator','./qakicons/symActorSmall.png')
     with Cluster('ctx_monitoringdevice', graph_attr=nodeattr):
          led=Custom('led','./qakicons/symActorSmall.png')
          sonar=Custom('sonar','./qakicons/symActorSmall.png')
     with Cluster('ctx_basicrobot', graph_attr=nodeattr):
          basicrobot=Custom('basicrobot','./qakicons/symActorSmall.png')
     with Cluster('ctx_oprobot', graph_attr=nodeattr):
          oprobot=Custom('oprobot','./qakicons/symActorSmall.png')
     wis >> Edge(color='blue', style='solid',  decorate='true', label='<led_on &nbsp; led_off &nbsp; >',  fontcolor='blue') >> led
     sonar >> Edge(color='blue', style='solid',  decorate='true', label='<ash_full &nbsp; >',  fontcolor='blue') >> wis
     oprobot >> Edge(color='blue', style='solid',  decorate='true', label='<ash_out &nbsp; >',  fontcolor='blue') >> ashstorage
     oprobot >> Edge(color='blue', style='solid',  decorate='true', label='<current_location &nbsp; job &nbsp; >',  fontcolor='blue') >> wis
     sonar >> Edge(color='blue', style='solid',  decorate='true', label='<ash_qty &nbsp; blink &nbsp; >',  fontcolor='blue') >> ashstorage
     incinerator >> Edge(color='blue', style='solid',  decorate='true', label='<burn_out &nbsp; >',  fontcolor='blue') >> oprobot
     oprobot >> Edge(color='blue', style='solid',  decorate='true', label='<burn_in &nbsp; >',  fontcolor='blue') >> incinerator
     wastestorage >> Edge(color='blue', style='solid',  decorate='true', label='<waste_qty &nbsp; >',  fontcolor='blue') >> wis
     wis >> Edge(color='blue', style='solid',  decorate='true', label='<start_robot &nbsp; >',  fontcolor='blue') >> oprobot
     oprobot >> Edge(color='blue', style='solid',  decorate='true', label='<get_waste &nbsp; >',  fontcolor='blue') >> wastestorage
     sonar >> Edge(color='blue', style='solid',  decorate='true', label='<blink &nbsp; >',  fontcolor='blue') >> led
     wis >> Edge(color='blue', style='solid',  decorate='true', label='<update_gui &nbsp; >',  fontcolor='blue') >> ssgui
     incinerator >> Edge(color='blue', style='solid',  decorate='true', label='<burning &nbsp; burn_out &nbsp; >',  fontcolor='blue') >> wis
diag
