package com.example.kcluster;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class EuclideanDistance implements Distance{
    @Override
    public double calculateDistance(Map<String, Double> point1, Map<String, Double> point2) {

        /*(8-4) + (15-6)
            pow(4) + pow(9)
            16 + 81
            sqrt(97)
            9.8
        */


        double powerofValue =0.0;
        double sum = 0.0;
        for (String key: point1.keySet()){
            double  firstValue = point1.get(key);
            log.info(" : {} has value {}", key, point1.get(key));
            double secondValue = point2.get(key);
            log.info(" : {} has value {}", key, point2.get(key));
            System.out.println();
            powerofValue = Math.pow(firstValue-secondValue, 2);
            log.info("Change in {}", powerofValue);
            sum += powerofValue;
            log.info("Sum of power {}", sum);
        }
        log.info("Square root of sum {}",Math.sqrt(sum));
        return Math.sqrt(sum);
    }
}
