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
with Diagram('wastestorageArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctx_wis', graph_attr=nodeattr):
          incinerator=Custom('incinerator','./qakicons/symActorSmall.png')
          op_robot=Custom('op_robot(ext)','./qakicons/externalQActor.png')
          wis=Custom('wis(ext)','./qakicons/externalQActor.png')
     incinerator >> Edge(color='blue', style='solid',  decorate='true', label='<burn_start &nbsp; burn_end &nbsp; >',  fontcolor='blue') >> wis
     incinerator >> Edge(color='blue', style='solid',  decorate='true', label='<burn_end &nbsp; >',  fontcolor='blue') >> op_robot
diag
