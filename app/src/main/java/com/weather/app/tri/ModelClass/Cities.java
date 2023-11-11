package com.weather.app.tri.ModelClass;

public class Cities {
    private String name;
    private String coordinates;

    public Cities(String name, String coordinates) {
        this.name = name;
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public String getCoordinates() {
        return coordinates;
    }
}
