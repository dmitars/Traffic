Project name
----
Traffic manager

Requirements
----
App requires python with version 3.0, jre version 15.0, Windows 10 system. Also must be installed Webots robot simulator.

Description
----
It is a traffic management system, which provides opportunity for simulating of current town's traffic part. User can set roads and number of cars to see in Webots what will be happened with these parameters.
In repository and wiki are results, which were got for Minsk aimed to analyze traffic jams dependence on building of the third metro line.

Installation
----
1. Clone repository
2. Change file minsk.osm with your osm file
3. Be sure, that requirements are met
4. Run executable files depending on what are you going to do

Usage
----
## A
1. Change minsk.osm to another file with the same name.
2. Run ourCreator.bat.
3. Open file world.wbt in Webots.
4. Add Sumo interface and set netConvert->false, netFiles->%MAP_PATH%/world_net.
5. Configure traffic lights.
6. Start simulation.
## B
2. You can run first_part.bat to create sumo.net.xml file.
3. Change sumo.net.xml with run do_netedit.bat using NETEDIT utilite or another way.
4. Write your file raw/routes.txt: first line - cars number, another - [+/-] depending on start node/end node, node name (from NETEDIT) and its priority.
5. Run trips.bat to generate cars and trips for them.
6. Go to items [3 - 6] from A variant.
## C
1. Run clearer.bat to remove all files except osm.
2. Go to A or B variants.

Contributing
----
* _Dmitriy Tarasenko ([@dmitars](github.com/dmitars))_: managed the project, wrote documentation, wrote java code for trips rendering, designed executable files and project structure.
* _Daniil Lebedev ([@alphaver](github.com/alphaver))_: designed scheme of traffic, changed map depending on real traffic lights and crossroads positions.
* _Viktor Graskov ([@ViktorHi](github.com/ViktoHi))_: designed jar files for roads modifying, decoding the file and trips rendering, wrote java code for them, collected data.
