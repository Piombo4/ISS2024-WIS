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
          oprobot=Custom('oprobot','./qakicons/symActorSmall.png')
          waste_storage=Custom('waste_storage(ext)','./qakicons/externalQActor.png')
          ash_storage=Custom('ash_storage(ext)','./qakicons/externalQActor.png')
          incinerator=Custom('incinerator(ext)','./qakicons/externalQActor.png')
     with Cluster('ctx_basic_robot', graph_attr=nodeattr):
          basic_robot=Custom('basic_robot(ext)','./qakicons/externalQActor.png')
     oprobot >> Edge(color='magenta', style='solid', decorate='true', label='<moverobot &nbsp; >',  fontcolor='magenta') >> basic_robot
     oprobot >> Edge(color='blue', style='solid',  decorate='true', label='<burn_in &nbsp; >',  fontcolor='blue') >> incinerator
     oprobot >> Edge(color='blue', style='solid',  decorate='true', label='<ash_out &nbsp; >',  fontcolor='blue') >> ash_storage
     oprobot >> Edge(color='blue', style='solid',  decorate='true', label='<get_waste &nbsp; >',  fontcolor='blue') >> waste_storage
diag
