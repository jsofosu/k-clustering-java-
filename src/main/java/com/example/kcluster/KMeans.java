package com.example.kcluster;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class KMeans {
    private Random random = new Random();

    public Map<Centroid, List<Location>> fitCentroid(List<Location> locationList, int k, Distance distance, int maxIterations){
        List<Centroid> centroids = generateRandomCentroids(locationList, k);
        Map<Centroid, List<Location>> clusters = new HashMap<>();
        Map<Centroid, List<Location>> previousClusters = new HashMap<>();

        for (int i=0; i<maxIterations; i++){
            boolean isLastIteration = i == maxIterations - 1;
            for(Location location: locationList){
                Centroid centroid = findNearestCentroid(location, centroids, distance);
                assignLocationToNearestCentroidCluster(clusters, location, centroid);
            }

            boolean endIteration = isLastIteration || clusters.equals(previousClusters);
            previousClusters = clusters;
            if(endIteration){
                break;
            }
            centroids = findCenterOfClusters(clusters);
            clusters = new HashMap<>();
        }
        return previousClusters;
    }

    public List<Centroid> generateRandomCentroids(List<Location> locationList, int k){
        List<Centroid> generatedCentroids = new ArrayList<>();
//        Map<String,Double> maxLocation = new HashMap<>();
//        Map<String, Double> minLocation = new HashMap<>();

//        for(Location location : locationList){
//            location.getCoordinates().forEach((key, value) -> {
//                maxLocation.compute(key, (k1, max) -> max == null || value > max?value:max );
//                minLocation.compute(key, (k1, min) -> min == null || value < min ? value:min);
//            });
//        }

        Set<String>  locationKeys = locationList.stream()
                .flatMap(e-> e.getCoordinates().keySet().stream())
                .collect(toSet());
        for(int i = 0; i< k; i++){
            Map<String, Double> locations = new HashMap<>();
            for(String locationKey:locationKeys){
//                double max = maxLocation.get(locationKey);
//                double min = minLocation.get(locationKey);
                //System.out.println(locationList.get(random.nextInt()).getCoordinates().get(locationKey));
                locations.put(locationKey, locationList.get(random.nextInt(locationList.size())).getCoordinates().get(locationKey));
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

    public Centroid findCenterOfClusterUsingMean(Centroid centroid, List<Location> clusterLocationList){
        if (clusterLocationList == null || clusterLocationList.isEmpty()){
            return centroid;
        }

        Map<String, Double> mean = centroid.getCoordinates();
        clusterLocationList.stream().flatMap((e-> e.getCoordinates().keySet().stream()))
                .forEach(key-> mean.put(key,0.0));

        for(Location location:clusterLocationList){
            location.getCoordinates().forEach(
                    (key,value)-> mean.compute(key,(meanKey, meaValue) -> value + meaValue)
            );
        }

        mean.forEach((key, value) -> mean.put(key, value/clusterLocationList.size()));

        return new Centroid(mean);
    }

    public List<Centroid> findCenterOfClusters(Map<Centroid, List<Location>> clusters){
        return clusters.entrySet().stream().map(e-> findCenterOfClusterUsingMean(e.getKey(),e.getValue()))
                .collect(toList());
    }

    /*
        List.of (locations)
        for each location in locations
        sum += location
     */



}
