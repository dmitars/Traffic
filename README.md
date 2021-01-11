Project name
----
Traffic manager

Requirements
----
The app requires Python 3, JRE 15.0, Windows 10, and the Webots robot simulator to be installed.

Description
----
We introduce a traffic management system which provides opportunity for simulation of current town's traffic part. A user can change roads and number of cars to see (using Webots) what will happen with these parameters.
In this repository we show the results obtained for the analysis of traffic in Minsk after building the third line of Minsk metro. 

Installation
----
1. Clone this repository.
2. Provide your own `.osm` file instead of `minsk.osm` if you wish. Check if your file meets the requirements.
3. Run the necessary executable files depending on what are you going to do.

Usage
----
## A
1. Replace `minsk.osm` with another file with the same name.
2. Run `ourCreator.bat`.
3. Open the file `world.wbt` in Webots.
4. Add the SumoInterface node, then set the `netConvert` property to `false` and `netFiles` to `%MAP_PATH%/world_net` where `%MAP_PATH%` stands for the path to the project folder.
5. Configure the traffic lights in both `NETEDIT` and Webots (if necessary).
6. Start the simulation in Webots.
## B
1. Run `first_part.bat` to create the `sumo.net.xml` file.
2. Edit `sumo.net.xml` using the `NETEDIT` utility or the way you like. To open `NETEDIT` you may run `do_netedit.bat`.
3. Create a file `routes.txt` in the `raw` folder. The file must have the following format: the first line contains the number of cars, succeeding lines contain either `+` or `-` standing for either start or finish nodes respectively, the node name (taken from `NETEDIT`) and its priority. (All fields are separated by spaces.)
4. Run `trips.bat` to generate cars and trips for them.
5. Now you're free to start the simulation. Go to items [3 - 6] from Variant A.
## C
1. Run `clearer.bat` to remove all files except the `osm` one.
2. Go to Variants A or B.

Contributing
----
* _Dmitriy Tarasenko ([@dmitars](github.com/dmitars))_: managed the project, wrote documentation, wrote Java code for trips rendering, designed executable files and project structure.
* _Daniil Lebedev ([@alphaver](github.com/alphaver))_: designed the scheme of traffic, changed the map depending on real traffic lights and crossroads positions.
* _Viktor Graskov ([@ViktorHi](github.com/ViktoHi))_: designed `.jar` files for the modification of roads, decoding the file and trips rendering, wrote Java code for them, collected the data.
