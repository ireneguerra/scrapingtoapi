package org.example.view;
import com.google.gson.Gson;
import org.example.controller.Controller;
import spark.Request;
import spark.Response;
import static spark.Spark.get;

public class WebService {
    public static void start(){
        get("/hotels/:name", WebService::showLocation);
        get("/hotels/:name/services", WebService::showServices);
        get("/hotels/:name/comments", WebService::showComments);
        get("/hotels/:name/ratings", WebService::showRatings);
    }

    private static String showLocation(Request request, Response response){
        response.header("content-type", "application/json");
        String name = request.params("name");
        return toJson( new Controller().getLocation(name));
    }

    private static String showServices(Request request, Response response){
        response.header("content-type", "application/json");
        String name = request.params("name");
        return toJson(new Controller().getServices(name));
    }

    private static String showComments(Request request, Response response){
        response.header("content-type", "application/json");
        String name = request.params("name");
        return toJson(new Controller().getReviews(name));
    }

    private static String showRatings(Request request, Response response){
        response.header("content-type", "application/json");
        String name = request.params("name");
        return toJson(new Controller().getRatings(name));
    }
    private static String toJson(Object o) {
        return new Gson().toJson(o);
    }
}

