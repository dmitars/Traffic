python %WEBOTS_HOME%/resources/osm_importer/importer.py --input=../world_net/minsk.osm --output=../world.wbt
python %WEBOTS_HOME%/resources/sumo_exporter/exporter.py --input=../world.wbt --output=../world_net
%WEBOTS_HOME%/projects/default/resources/sumo/bin/netconvert --node-files=../world_net/sumo.nod.xml --edge-files=../world_net/sumo.edg.xml --output-file=../world_net/sumo.net.xml
%WEBOTS_HOME%/projects/default/resources/sumo/bin/netedit ../world_net/sumo.net.xml
java -jar ../TrackCreator.jar -cs ../raw/routes.txt ../world_net/sumo.trip.temp.xml
java -jar ../TrackCreator.jar -t ../world_net/sumo.trip.temp.xml
move ../sumo.trip.xml ../world_net
java -jar ../TrackCreator.jar -f ../world_net/sumo.edg.xml ../world_net/sumo.trip.xml ../world_net/sumo.trip.res.xml
%WEBOTS_HOME%/projects/default/resources/sumo/bin/duarouter --trip-files ../world_net/sumo.trip.res.xml --net-file ../world_net/sumo.net.xml --output-file ../world_net/sumo.rou.xml --ignore-errors true
 