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
with Diagram('sprint2_architetturalogicaArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctx_wis', graph_attr=nodeattr):
          oprobot=Custom('oprobot','./qakicons/symActorSmall.png')
          wis=Custom('wis','./qakicons/symActorSmall.png')
          wastestorage=Custom('wastestorage','./qakicons/symActorSmall.png')
          ashstorage=Custom('ashstorage','./qakicons/symActorSmall.png')
          incinerator=Custom('incinerator','./qakicons/symActorSmall.png')
     with Cluster('ctx_basic_robot', graph_attr=nodeattr):
          basic_robot=Custom('basic_robot(ext)','./qakicons/externalQActor.png')
     with Cluster('ctx_monitoring_device', graph_attr=nodeattr):
          led=Custom('led','./qakicons/symActorSmall.png')
          sonar24=Custom('sonar24','./qakicons/symActorSmall.png')
          sonardevice=Custom('sonardevice','./qakicons/symActorSmall.png')
     sonardevice >> Edge( label='sonardata', **eventedgeattr, decorate='true', fontcolor='red') >> sonar24
     oprobot >> Edge(color='magenta', style='solid', decorate='true', label='<moverobot &nbsp; >',  fontcolor='magenta') >> basic_robot
     wis >> Edge(color='blue', style='solid',  decorate='true', label='<led_status &nbsp; >',  fontcolor='blue') >> led
     oprobot >> Edge(color='blue', style='solid',  decorate='true', label='<deposit_ash &nbsp; >',  fontcolor='blue') >> ashstorage
     oprobot >> Edge(color='blue', style='solid',  decorate='true', label='<waiting &nbsp; >',  fontcolor='blue') >> wis
     incinerator >> Edge(color='blue', style='solid',  decorate='true', label='<burn_end &nbsp; >',  fontcolor='blue') >> oprobot
     oprobot >> Edge(color='blue', style='solid',  decorate='true', label='<burn_in &nbsp; >',  fontcolor='blue') >> incinerator
     wastestorage >> Edge(color='blue', style='solid',  decorate='true', label='<waste_qty &nbsp; >',  fontcolor='blue') >> wis
     wis >> Edge(color='blue', style='solid',  decorate='true', label='<start_robot &nbsp; >',  fontcolor='blue') >> oprobot
     oprobot >> Edge(color='blue', style='solid',  decorate='true', label='<get_waste &nbsp; >',  fontcolor='blue') >> wastestorage
     incinerator >> Edge(color='blue', style='solid',  decorate='true', label='<burn_start &nbsp; burn_end &nbsp; >',  fontcolor='blue') >> wis
diag
