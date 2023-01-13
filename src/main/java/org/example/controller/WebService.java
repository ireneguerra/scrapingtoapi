package org.example.controller;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import static spark.Spark.get;

public class WebService {
    private final Controller controller;

    public WebService(Controller controller) {
        this.controller = controller;
    }

    public void start() {
        WebService webService = new WebService(controller);
        get("/hotels/:name", webService::showLocation);
        get("/hotels/:name/services", webService::showServices);
        get("/hotels/:name/comments", webService::showComments);
        get("/hotels/:name/ratings", webService::showRatings);
    }

    private String showLocation(Request request, Response response) {
        response.header("content-type", "application/json");
        String name = request.params("name");
        return toJson(controller.scrapHotel(name));
    }

    private String showServices(Request request, Response response) {
        response.header("content-type", "application/json");
        String name = request.params("name");
        return toJson(controller.scrapServices(name));
    }

    private String showComments(Request request, Response response) {
        response.header("content-type", "application/json");
        String name = request.params("name");
        return toJson(controller.scrapComments(name));
    }

    private String showRatings(Request request, Response response) {
        response.header("content-type", "application/json");
        String name = request.params("name");
        return toJson(controller.scrapRatings(name));
    }

    private String toJson(Object o) {
        return new Gson().toJson(o);
    }
}

