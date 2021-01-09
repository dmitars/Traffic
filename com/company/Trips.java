package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Trips {

    static class Trip {
        private static int generalId = 0;
        public String startId;
        public String endId;
        public int id;
        public double depart;

        public Trip(String startId, String endId, double depart) {
            this.startId = startId;
            this.endId = endId;
            this.depart = depart;
            this.id = generalId++;
        }

    }

    public int amounts;
    public List<Trip> trips;

    public Trips(List<String> trips) {
        this.trips = new ArrayList<>();
        amounts = Integer.parseInt(trips.get(0));

        int sum = trips.subList(1, trips.size()).stream().mapToInt(e -> Integer.parseInt(e.split(" ")[2])).sum();

        trips.subList(1, trips.size()).forEach(e -> parseEachRecord(e, sum));
    }

    private void parseEachRecord(String s, int sum){
        String[] strings = s.trim().split(" ");
        var weight = Integer.parseInt(strings[2]);

        for (int i = 0; i < (double)weight / sum * amounts; i++) {
            Trip trip = new Trip(strings[0], strings[1], new Random().nextInt(15));
            trips.add(trip);
        }
    }



}
