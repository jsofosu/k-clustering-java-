package com.example.kcluster;

import java.util.Map;

public interface Distance {
    double calculateDistance(Map<String, Double> point1, Map<String, Double> point2);
}
