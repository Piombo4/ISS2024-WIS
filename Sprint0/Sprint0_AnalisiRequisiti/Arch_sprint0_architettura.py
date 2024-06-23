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
with Diagram('sprint0_architetturaArch', show=False, outformat='png', graph_attr=graphattr) as diag:
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
          oprobot=Custom('oprobot(ext)','./qakicons/externalQActor.png')
diag
