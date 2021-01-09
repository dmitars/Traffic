package com.company;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    private static String message = "use one of keys:\n" +
            "-t [input] \tto make sumo.trip.xml\n" +
            "-r [input] [output] \treplace all cyrillic symbols for latin analogue\n" +
            "-e [input] [output] \tto make sumo.edg.xml (add symmetrical ways)\n" +
            "-f [input] [output] [] \tfind edge for trips \n" +
            "-cs [input] [output] \tto make pre-file for -t mode, file in format\n" +
            "\tsum of cars\n" +
            "\trecord begin from \"'+' if node is input '-' otherwise\" \"node name\" \"node weight\"";

    public static void main(String[] args) throws IOException, TransformerException, ParserConfigurationException {
        System.out.println("Script begin!");

        if (args.length == 0) {
            System.out.println(message);
            return;
        }

        switch (args[0]) {
            case "-cs" -> generateTripCartesianFile(args);
            case "-e" -> generateEdgXmlFile(args);
            case "-t" -> generateTripFile(args);
            case "-r" -> replaceCyrillic(args);
            case "-f" -> findEdgeForTrips(args);
            default -> System.out.println(message);
        }

        System.out.println("Script finished!");
    }

    private static void findEdgeForTrips(String[] args) throws IOException {
        if (args.length != 4) {
            throw new RuntimeException("cannot find edge or trip file");
        }
        EdgeForTripsFinder.find(args);
        System.out.println(args[2] + " generated successfully!!!");
    }

    private static void replaceCyrillic(String[] args) throws IOException {
        if (args.length != 3) throw new RuntimeException("Not enough arguments");
        CyrillicReplacer.replace(args);
    }

    private static void generateTripCartesianFile(String[] args) throws IOException {
        if (args.length != 3) throw new RuntimeException("Not enough arguments");

        Node.generateTripCartesianFile(args);

        System.out.println(args[2] + " generated successfully!!!");
    }

    private static void generateEdgXmlFile(String[] args) throws IOException {
        if (args.length < 2)
            throw new RuntimeException("File with input nodes not found");
        if (args.length < 3)
            throw new RuntimeException("Output file path not found");
        EdgeAdder.addSymmetricalEdges(args);

        System.out.println(args[1] + " generated successfully!!!");
    }

    private static void generateTripFile(String[] args) throws TransformerException, ParserConfigurationException, IOException {
        var outputFile = "sumo.trip.xml";
        List<String> strings = FileWorker.readFile(Paths.get(args[1]));
        Trips trips = new Trips(strings);

        XMLWorker.generateTripFile(outputFile, trips);
        System.out.println(outputFile + " generated successfully!!!");
    }


}
