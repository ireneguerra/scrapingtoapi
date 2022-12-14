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
        Hotel hotel = BookingScrapper.scrapHotel(name);
        return toJson(hotel);
    }

    private static String getServices(Request request, Response response){
        response.header("content-type", "application/json");
        String name = request.params("name");
        HashMap<String, List<String>> services = BookingScrapper.scrapServices(name);
        return toJson(services);
    }

    private static String getComments(Request request, Response response){
        response.header("content-type", "application/json");
        String name = request.params("name");
        List<Review> comments = BookingScrapper.scrapComments(name);
        return toJson(comments);
    }

    private static String getRatings(Request request, Response response){
        response.header("content-type", "application/json");
        String name = request.params("name");
        HashMap<String, Double> ratings = BookingScrapper.scrapRatings(name);
        return toJson(ratings);
    }
    private static String toJson(Object o) {
        return new Gson().toJson(o);
    }
}

