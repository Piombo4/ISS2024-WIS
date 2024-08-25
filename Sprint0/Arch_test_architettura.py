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
with Diagram('test_architetturaArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctx_wastestorage', graph_attr=nodeattr):
          waste_storage=Custom('waste_storage(ext)','./qakicons/externalQActor.png')
     with Cluster('ctx_ashstorage', graph_attr=nodeattr):
          ash_storage=Custom('ash_storage(ext)','./qakicons/externalQActor.png')
     with Cluster('ctx_test', graph_attr=nodeattr):
          test_manager=Custom('test_manager','./qakicons/symActorSmall.png')
     ash_storage >> Edge(color='blue', style='solid',  decorate='true', label='<qty &nbsp; >',  fontcolor='blue') >> test_manager
     test_manager >> Edge(color='blue', style='solid',  decorate='true', label='<get_waste &nbsp; >',  fontcolor='blue') >> waste_storage
diag
