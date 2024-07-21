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
     with Cluster('ctx_wis', graph_attr=nodeattr):
          waste_storage=Custom('waste_storage(ext)','./qakicons/externalQActor.png')
          ash_storage=Custom('ash_storage(ext)','./qakicons/externalQActor.png')
     with Cluster('ctx_test', graph_attr=nodeattr):
          test_manager=Custom('test_manager','./qakicons/symActorSmall.png')
          test_manager_ash=Custom('test_manager_ash','./qakicons/symActorSmall.png')
     test_manager_ash >> Edge(color='magenta', style='solid', decorate='true', label='<empty_ash<font color="darkgreen"> ashes_taken</font> &nbsp; >',  fontcolor='magenta') >> ash_storage
     waste_storage >> Edge(color='blue', style='solid',  decorate='true', label='<waste_qty &nbsp; >',  fontcolor='blue') >> test_manager
     test_manager >> Edge(color='blue', style='solid',  decorate='true', label='<waste_in &nbsp; get_waste &nbsp; >',  fontcolor='blue') >> waste_storage
     test_manager_ash >> Edge(color='blue', style='solid',  decorate='true', label='<deposit_ash &nbsp; >',  fontcolor='blue') >> ash_storage
diag
