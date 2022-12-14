package org.example;

import java.util.HashMap;
import java.util.List;

public class Hotel {
    private final Location location;
    private final List<String> services;
    private final HashMap<String, Double> rating;
    private final List<Review> reviews;

    public Hotel(Location location, List<String> services, HashMap<String, Double> rating, List<Review> reviews) {
        this.location = location;
        this.services = services;
        this.rating = rating;
        this.reviews = reviews;
    }

    public Location getLocation() {
        return location;
    }

    public List<String> getServices() {
        return services;
    }

    public HashMap<String, Double> getRating() {
        return rating;
    }

    public List<Review> getReviews() {
        return reviews;
    }
}
