package com.example.kcluster;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EuclideanDistanceTest {

    @Test
    void calculateDistance() {
        Map<String, Double> point1 = Map.of("latitude", 8.0, "longitude", 6.0);
        Map<String, Double> point2 = Map.of("latitude", 4.0, "longitude", 3.0);
        EuclideanDistance distance = new EuclideanDistance();
        double result = distance.calculateDistance(point1, point2);
        assertEquals(result, 5);
    }
}

/*
        Average
(k1,currentValue)
latitude: currentValue
longitude: currentValue

location to be added to the average

(k,v)
latitude: 8.0
longitude:6.0

(k:latitude, v:8.0) -> average.compute(k,(k1, currentValue) -> v+currentValue)

 */