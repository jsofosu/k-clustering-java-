package com.example.kcluster;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@SpringBootApplication
public class KClusterApplication {

    public static void main(String[] args) {
        SpringApplication.run(KClusterApplication.class, args);
        KMeans kMeans = new KMeans();
        Map<String, Double> point1 = Map.of("latitude", 5.5886944, "longitude", -0.1801485);
        Map<String, Double> point2 = Map.of("latitude", 5.588798, "longitude", -0.1797093);
        Location location = new Location(1, point1);
        Location location2 = new Location(2, point2);
        List<Location> locationList = List.of(location,location2);

        List<Centroid> generatedCentroids = kMeans.generateRandomCentroids(locationList, 2);
        System.out.println(generatedCentroids);

    }

}
