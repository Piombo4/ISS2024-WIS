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
     with Cluster('ctx_wastestorage', graph_attr=nodeattr):
          waste_storage=Custom('waste_storage','./qakicons/symActorSmall.png')
     with Cluster('ctx_oprobot', graph_attr=nodeattr):
          op_robot=Custom('op_robot(ext)','./qakicons/externalQActor.png')
     with Cluster('ctx_mock_external_entity', graph_attr=nodeattr):
          mock_external_entity=Custom('mock_external_entity(ext)','./qakicons/externalQActor.png')
diag
