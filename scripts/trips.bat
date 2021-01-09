java -jar ../TrackCreator.jar -cs ../raw/routes.txt ../world_net/sumo.trip.temp.xml
java -jar ../TrackCreator.jar -t ../world_net/sumo.trip.temp.xml
move ../sumo.trip.xml ../world_net
java -jar ../TrackCreator.jar -f ../world_net/sumo.edg.xml ../world_net/sumo.trip.xml ../world_net/sumo.trip.res.xml
%WEBOTS_HOME%/projects/default/resources/sumo/bin/duarouter --trip-files ../world_net/sumo.trip.res.xml --net-file ../world_net/sumo.net.xml --output-file ../world_net/sumo.rou.xml --ignore-errors true
