package org.example;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;

import static spark.Spark.get;
import static spark.Spark.halt;

public class WebService {
    public static void gets(){
        get("/hotels/:name", (request, response) -> getLocation(request, response));
        get("/hotels/:name/services", (request, response) -> getServices(request, response));
        get("/hotels/:name/comments", (request, response) -> getComments(request, response));
        get("/hotels/:name/ratings", (request, response) -> getRatings(request, response));
    }

    private static String getLocation(Request request, Response response){
        response.header("content-type", "application/json");
        String name = request.params("name");
        Hotel hotel = Scrapper.getWebUrl(name);
        Location location = hotel.getLocation();
        return toJson(location);
    }

    private static String getServices(Request request, Response response){
        response.header("content-type", "application/json");
        String name = request.params("name");
        Hotel hotel = Scrapper.getWebUrl(name);
        List<String> services = hotel.getServices();
        return toJson(services);
    }

    private static String getComments(Request request, Response response){
        response.header("content-type", "application/json");
        String name = request.params("name");
        Hotel hotel = Scrapper.getWebUrl(name);
        List<Review> comments = hotel.getReviews();
        return toJson(comments);
    }

    private static String getRatings(Request request, Response response){
        response.header("content-type", "application/json");
        String name = request.params("name");
        Hotel hotel = Scrapper.getWebUrl(name);
        HashMap<String, Double> ratings = hotel.getRating();
        return toJson(ratings);
    }
    private static String toJson(Object o) {
        return new Gson().toJson(o);
    }
}

