package com.example.kcluster;

import java.util.HashMap;
import java.util.Map;

public class Location {
    private long id ;
    private Map<String, Double> coordinates;

    public Location(long id, Map<String, Double> coordinates) {
        this.id = id;
        this.coordinates = coordinates;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Map<String, Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Map<String, Double> coordinates) {
        this.coordinates = coordinates;
    }


}
