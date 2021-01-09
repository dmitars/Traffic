package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EdgeForTripsFinder {

        private static final Map<String,String> startEdges = new HashMap<>();
        private static final Map<String,String>endEdges = new HashMap<>();

        public static void find(String[] args) throws IOException {


            readEdgeFile(args[1]);
            var tripLines = Files.readAllLines(Path.of(args[2]));
            for(int i = 0;i< tripLines.size();i++){
                String tripLine = tripLines.get(i);
                if(isTripLine(tripLine)){
                    String from = getFrom(tripLine);
                    String to = getTo(tripLine);
                    tripLine = tripLine.replace(from,startEdges.get(from));
                    tripLine = tripLine.replace(to, endEdges.get(to));
                    tripLines.set(i,tripLine);
                }
            }

            FileWorker.writeLinesToFile(tripLines, Path.of(args[3]));
        }

        private static void readEdgeFile(String edgeFileName) throws IOException {
            var lines = Files.readAllLines(Path.of(edgeFileName));
            for(var line:lines){
                if(isEdgeLine(line)){
                    String edgeId = getEdgeId(line);
                    startEdges.put(getFrom(line),edgeId);
                    endEdges.put(getTo(line),edgeId);
                }
            }
        }

        private static boolean isTripLine(String s){
            return s.contains("trip");
        }

        private static boolean isEdgeLine(String s){
            return s.contains("edge ");
        }

        private static String getFrom(String s){
            Pattern pattern = Pattern.compile("from=\"([-\\d\\w]+)\"");
            Matcher matcher = pattern.matcher(s);
            if(matcher.find()){
                return matcher.group(1);
            }
            throw new RuntimeException("from not found");
        }

        private static String getTo(String s){
            Pattern pattern = Pattern.compile("to=\"([-\\d\\w]+)\"");
            Matcher matcher = pattern.matcher(s);
            if(matcher.find()){
                return matcher.group(1);
            }
            throw new RuntimeException("to not found");
        }

        private static String getEdgeId(String s){
            Pattern pattern = Pattern.compile("id=\"([-\\d\\w]+)\"");
            Matcher matcher = pattern.matcher(s);
            if(matcher.find()){
                return matcher.group(1);
            }
            throw new RuntimeException("id not found");
        }

}
