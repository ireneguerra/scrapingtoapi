package org.example.model;

import java.util.HashMap;
import java.util.List;

public interface HotelScrapper {
    Hotel scrapHotel(String name);
    HashMap<String, List<String>> scrapServices(String name);
    List<Review> scrapComments(String name);
    HashMap<String, Double> scrapRatings(String name);
}
