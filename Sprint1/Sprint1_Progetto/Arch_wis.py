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
with Diagram('wisArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctx_wis', graph_attr=nodeattr):
          mock=Custom('mock','./qakicons/symActorSmall.png')
          wis=Custom('wis','./qakicons/symActorSmall.png')
          op_robot=Custom('op_robot','./qakicons/symActorSmall.png')
          incinerator=Custom('incinerator','./qakicons/symActorSmall.png')
          ash_storage=Custom('ash_storage','./qakicons/symActorSmall.png')
          waste_storage=Custom('waste_storage','./qakicons/symActorSmall.png')
     with Cluster('ctx_basic_robot', graph_attr=nodeattr):
          basicrobot=Custom('basicrobot(ext)','./qakicons/externalQActor.png')
     op_robot >> Edge(color='magenta', style='solid', decorate='true', label='<engage<font color="darkgreen"> engagedone engagerefused</font> &nbsp; moverobot &nbsp; >',  fontcolor='magenta') >> basicrobot
     mock >> Edge(color='magenta', style='solid', decorate='true', label='<empty_ash<font color="darkgreen"> ashes_taken</font> &nbsp; >',  fontcolor='magenta') >> ash_storage
     op_robot >> Edge(color='blue', style='solid',  decorate='true', label='<burn_in &nbsp; get_ash &nbsp; >',  fontcolor='blue') >> incinerator
     op_robot >> Edge(color='blue', style='solid',  decorate='true', label='<get_waste &nbsp; >',  fontcolor='blue') >> waste_storage
     op_robot >> Edge(color='blue', style='solid',  decorate='true', label='<waiting &nbsp; >',  fontcolor='blue') >> wis
     mock >> Edge(color='blue', style='solid',  decorate='true', label='<waste_in &nbsp; >',  fontcolor='blue') >> waste_storage
     incinerator >> Edge(color='blue', style='solid',  decorate='true', label='<burn_end &nbsp; >',  fontcolor='blue') >> op_robot
     wis >> Edge(color='blue', style='solid',  decorate='true', label='<start_robot &nbsp; >',  fontcolor='blue') >> op_robot
     op_robot >> Edge(color='blue', style='solid',  decorate='true', label='<deposit_ash &nbsp; >',  fontcolor='blue') >> ash_storage
     waste_storage >> Edge(color='blue', style='solid',  decorate='true', label='<waste_qty &nbsp; >',  fontcolor='blue') >> wis
     incinerator >> Edge(color='blue', style='solid',  decorate='true', label='<burn_start &nbsp; burn_end &nbsp; >',  fontcolor='blue') >> wis
diag
