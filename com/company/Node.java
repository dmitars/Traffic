package com.company;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class Node {
    public String id;
    public int weight;

    public Node(String id, int weight) {
        this.id = id;
        this.weight = weight;
    }

    public static Node parseNode(List<String> nodes) {
        return new Node(nodes.get(0), Integer.parseInt(nodes.get(1)));
    }

    public static void generateTripCartesianFile(String[] args) throws IOException {

        List<String> strings = FileWorker.readFile(Path.of(args[1]));
        int sum = Integer.parseInt(strings.get(0));
        var in = new HashSet<Node>();
        var out = new HashSet<Node>();

        for (int i = 1; i < strings.size(); i++) {
            String[] nodes = strings.get(i).split(" ");
            Node e = Node.parseNode(Arrays.asList(nodes).subList(1, 3));
            switch (nodes[0]) {
                case "+" -> in.add(e);
                case "-" -> out.add(e);
            }
        }

        var ans = cartesianSet(in, out, sum);


        FileWorker.writeLinesToFile(ans, Path.of(args[2]));
    }

    private static List<String> cartesianSet(Set<Node> a, Set<Node> b, int sum) {
        var ans = new ArrayList<String>();
        ans.add(String.valueOf(sum));
        for (var i : a) {
            for (var j : b) {
                if (!i.id.equals(j.id)) {
                    ans.add(i.id + " " + j.id + " " + (j.weight + i.weight));
                }
            }
        }
        return ans;
    }

}
