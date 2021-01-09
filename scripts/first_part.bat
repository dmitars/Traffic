clearer
python %WEBOTS_HOME%/resources/osm_importer/importer.py --input=../world_net/minsk.osm --output=../world.wbt
python %WEBOTS_HOME%/resources/sumo_exporter/exporter.py --input=../world.wbt --output=../world_net
%WEBOTS_HOME%/projects/default/resources/sumo/bin/netconvert --node-files=../world_net/sumo.nod.xml --edge-files=../world_net/sumo.edg.xml --output-file=../world_net/sumo.net.xml
%WEBOTS_HOME%/projects/default/resources/sumo/bin/netedit ../world_net/sumo.net.xml
