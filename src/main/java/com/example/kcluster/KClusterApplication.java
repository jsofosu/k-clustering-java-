package com.example.kcluster;

import lombok.extern.slf4j.Slf4j;
import static java.util.stream.Collectors.toSet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@SpringBootApplication
public class KClusterApplication {

    public static void main(String[] args) {
        SpringApplication.run(KClusterApplication.class, args);

        /*
            Locations

Dansoman

Pizza Man Chicken Man (5.53913220702999, -0.264491303779941)
Dansoman Police Station - (5.544470970784894, -0.2649036514726285)
Burger King - (5.545781278363159, -0.2643876571337587)

Mataheko

Thomas Eye Clinic - ( 5.562648281823999, -0.24825504102584908 )
Jovix - (5.56965321254956, -0.25658061726295717)
Nap Lodge - (5.565253784097915, -0.2570526860186695)
Mataheko Pharmacy - (5.568898191639492, -0.25152432838932154)


Ridge

Cal Bank - ( 5.564601700957673, -0.19498874963012577)
Ridge Hospital - ( 5.562038909087668, -0.19863655365153912 )
Baffy Eatery - ( 5.564014170084097,  -0.18623759027055875 )
Papaye Osu - ( 5.560788594700213,  -0.18220145757480652)

         */

        KMeans kMeans = new KMeans();
        Map<String, Double> dpizzaMan = Map.of("latitude", 5.53913220702999, "longitude", -0.264491303779941);
        Map<String, Double> meyeclinic = Map.of("latitude", 5.562648281823999, "longitude", -0.24825504102584908);
        Map<String, Double> mjovix = Map.of("latitude", 5.56965321254956, "longitude", -0.25658061726295717);
        Map<String, Double> rcalbank = Map.of("latitude", 5.564601700957673, "longitude", -0.19498874963012577);
        Map<String, Double> dpolice = Map.of("latitude", 5.544470970784894, "longitude", -0.2649036514726285);
        Map<String, Double> mpharmacy = Map.of("latitude", 5.568898191639492, "longitude", -0.25152432838932154);
        Map<String, Double> rhospital = Map.of("latitude", 5.562038909087668, "longitude", -0.19863655365153912);
        Map<String, Double> rbaffyeatery = Map.of("latitude", 5.564014170084097, "longitude", -0.18623759027055875);
        Map<String, Double> dburgerking = Map.of("latitude", 5.545781278363159, "longitude", -0.2643876571337587);
        Map<String, Double> mnaplodge = Map.of("latitude", 5.565253784097915, "longitude", -0.2570526860186695);
        Map<String, Double> rpapaye = Map.of("latitude", 5.560788594700213, "longitude", -0.18220145757480652);

        Location location1 = new Location(1, dpizzaMan);
        Location location2 = new Location(2, meyeclinic);
        Location location3 = new Location(3, mjovix);
        Location location4 = new Location(4, rcalbank);
        Location location5 = new Location(5, dpolice);
        Location location6 = new Location(6, mpharmacy);
        Location location7 = new Location(7, rhospital);
        Location location8 = new Location(8, rbaffyeatery);
        Location location9 = new Location(9, dburgerking);
        Location location10 = new Location(10, mnaplodge);
        Location location11 = new Location(11, rpapaye);




        List<Location> locationList = List.of(location1,location2, location3,location4,location5,location6, location7, location8, location9, location10, location11);

        List<Centroid> generatedCentroids = kMeans.generateRandomCentroids(locationList, 3);
        for (Centroid centroid: generatedCentroids){
            System.out.println(centroid.getCoordinates());
        }

        Map<Centroid, List<Location>> clusters = kMeans.fitCentroid(locationList, 3, new EuclideanDistance(), 1000);

        // Printing the cluster configuration
        clusters.forEach((key, value) -> {
            System.out.println("-------- CLUSTER -----------");

            // Sorting the coordinates to see the most significant tags first.
           // System.out.println(sortedCentroid(key));
            Set<Long> members =  value.stream().map(Location::getId)
                    .collect(toSet());
            System.out.print(members);

            System.out.println();
            System.out.println();
        });


    }

}
