package org.example.controller;

import org.example.model.BookingScrapper;
import org.example.model.Hotel;
import org.example.model.Review;
import org.example.view.WebService;

import java.util.HashMap;
import java.util.List;

public class Controller {
    public void control(){
        new WebService().start();
    }
    public Hotel getLocation(String name){
        return new BookingScrapper().scrapHotel(name);
    }
    public HashMap<String, List<String>> getServices(String name){
        return new BookingScrapper().scrapServices(name);
    }
    public List<Review> getReviews(String name){
        return new BookingScrapper().scrapComments(name);
    }
    public HashMap<String, Double> getRatings(String name){
        return new BookingScrapper().scrapRatings(name);
    }
}
