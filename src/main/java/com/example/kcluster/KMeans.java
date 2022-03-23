package com.example.kcluster;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

public class KMeans {
    private Random random = new Random();

//    public Map<Centroid, List<Location>> fitCentroid(List<Location> locationList, int k, Distance distance, int maxIterations){
//
//    }

    public List<Centroid> generateRandomCentroids(List<Location> locationList, int k){
        List<Centroid> generatedCentroids = new ArrayList<>();
        Map<String,Double> maxLocation = new HashMap<>();
        Map<String, Double> minLocation = new HashMap<>();

        for(Location location : locationList){
            location.getCoordinates().forEach((key, value) -> {
                maxLocation.compute(key, (k1, max) -> max == null || value > max?value:max );
                minLocation.compute(key, (k1, min) -> min == null || value < min ? value:min);
            });
        }

        Set<String>  locationKeys = locationList.stream()
                .flatMap(e-> e.getCoordinates().keySet().stream())
                .collect(toSet());
        for(int i = 0; i< k; i++){
            Map<String, Double> locations = new HashMap<>();
            for(String locationKey:locationKeys){
                double max = maxLocation.get(locationKey);
                double min = minLocation.get(locationKey);
                locations.put(locationKey,random.nextDouble()*(max - min) + min);
            }
            generatedCentroids.add(new Centroid(locations));
        }
        return generatedCentroids;
    }

    private Centroid findNearestCentroid(Location location, List<Centroid> centroidList, Distance distance){
        double minDistance = Double.MAX_VALUE;
        Centroid nearestCentroid = null;
        for (Centroid centroid: centroidList){
            //calculating the distance between the centroids and the location
            double currentDistance = distance.calculateDistance(location.getCoordinates(), centroid.getCoordinates());
            if(currentDistance<minDistance){
                minDistance = currentDistance;
                nearestCentroid = centroid;
            }
        }
        return nearestCentroid;
    }

    private void assignLocationToNearestCentroidCluster(Map<Centroid, List<Location>> clusters, Location location, Centroid centroid){
        clusters .compute(centroid, (key, list) -> {
            if(list == null){
                list = new ArrayList<>();
            }
            list.add(location);
            return list;
        });
    }
}
