package org.example.controller;

import org.example.model.BookingScraper;
import org.example.model.Hotel;
import org.example.model.HotelScraper;
import org.example.model.Review;

import java.util.HashMap;
import java.util.List;

public class Controller implements HotelScraper {
    public void control(){
        new WebService(new Controller()).start();
    }
    @Override
    public Hotel scrapHotel(String name) {
        return new BookingScraper().scrapHotel(name);
    }

    @Override
    public HashMap<String, List<String>> scrapServices(String name) {
        return new BookingScraper().scrapServices(name);
    }

    @Override
    public List<Review> scrapComments(String name) {
        return new BookingScraper().scrapComments(name);
    }

    @Override
    public HashMap<String, Double> scrapRatings(String name) {
        return new BookingScraper().scrapRatings(name);
    }
}
