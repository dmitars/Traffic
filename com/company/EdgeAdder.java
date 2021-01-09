package com.company;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EdgeAdder {
    static public void addSymmetricalEdges(String[] args) throws IOException {
        List<String> strings = FileWorker.readFile(Paths.get(args[1]));
        for (int i = 0; i < strings.size(); i++) {
            if (!isEdgeLine(strings.get(i)))
                continue;
            if (i + 1 < strings.size() && isLineSymmetric(strings.get(i + 1)))
                continue;
            strings.add(i + 1, generateSymmetric(strings.get(i)));
            i++;
        }

        FileWorker.writeLinesToFile(strings, Paths.get(args[2]));
    }

    private static String getIdValue(String line) {

        Pattern compile = Pattern.compile("<edge id=\"([-\\d_]+)\".+");
        Matcher matcher = compile.matcher(line.trim());
        if (matcher.find()) {
            return matcher.group(1);
        }
        throw new RuntimeException("Unreachable statement");
    }

    private static String getFromValue(String line) {

        Pattern compile = Pattern.compile("from=\"([\\w\\d]+)\"");
        Matcher matcher = compile.matcher(line.trim());
        if (matcher.find()) {
            return matcher.group(1);
        }
        throw new RuntimeException("Unreachable statement");
    }

    private static String getToValue(String line) {
        Pattern compile = Pattern.compile("to=\"([\\w\\d]+)\"");
        Matcher matcher = compile.matcher(line.trim());
        if (matcher.find()) {
            return matcher.group(1);
        }
        throw new RuntimeException("Unreachable statement");
    }

    private static String getReversedCoordinates(String line) {
        String[] coordinates = getCoordinates(line);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = coordinates.length - 1; i >= 0; i--)
            stringBuilder.append(coordinates[i]).append(" ");
        return stringBuilder.toString();
    }

    private static String[] getCoordinates(String line) {
        Pattern compile = Pattern.compile("shape=\"([\\w\\s-.,\\d]+)\"");
        Matcher matcher = compile.matcher(line.trim());
        if (matcher.find()) {
            return matcher.group(1).split(" ");
        }
        throw new RuntimeException("Unreachable statement");
    }


    private static String generateSymmetric(String s) {
        return "  <edge id=\"-" + getIdValue(s) +
                "\" to=\"" + getFromValue(s) +
                "\" from=\"" + getToValue(s) +
                "\" " +
                s.substring(s.indexOf("numLanes"), s.indexOf("shape")) +
                "shape=\"" + getReversedCoordinates(s) + "\"" +
                "/>";
    }

    private static boolean isEdgeLine(String line) {
        return line.contains("edge ");
    }

    private static boolean isLineSymmetric(String line) {
        if (!isEdgeLine(line)) return false;

        String idValue = getIdValue(line);
        return idValue.charAt(0) == '-';
    }

}
