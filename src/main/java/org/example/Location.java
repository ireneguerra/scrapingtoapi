package org.example;

public class Location {
    private final String address;
    private final String coordinates;

    public Location(String address, String coordinates) {
        this.address = address;
        this.coordinates = coordinates;
    }

    public String getAddress() {
        return address;
    }

    public String getCoordinates() {
        return coordinates;
    }
}
