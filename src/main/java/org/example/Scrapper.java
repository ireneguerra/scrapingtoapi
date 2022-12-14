package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Scrapper {

    public static Document getHTML(String url){
        Document html = null;
        try{
            html = Jsoup.connect(url).get();
        }catch (Exception e){
            System.out.println("Error al obtener el código HTML");
        }
        return html;
    }

    public static Hotel getWebUrl(String name){
        String url = "https://www.google.com/search?q=booking+" + name;
        Elements urls = getHTML(url).select("div.Z26q7c.UK95Uc.jGGQ5e.VGXe8");
        String web = urls.select("a").attr("href");
        System.out.println(web);
        Hotel hotel = scrapper(web);
        return hotel;
    }
    public static Hotel scrapper(String url){
        Elements locations = getHTML(url).select("span.hp_address_subtitle.js-hp_address_subtitle.jq_tooltip");
        String address = locations.select("span").attr("title aria-describedby", "tooltip-1").text();
        System.out.println(address);

        Elements coordinates = getHTML(url).select("div.k2-hp--gallery-header.bui-grid__column.bui-grid__column-9");
        String coordinate = coordinates.select("a").attr("data-atlas-latlng");
        System.out.println(coordinate);
        Location location = new Location(address, coordinate);
        List<String> services = new ArrayList<>();
        Elements facilities = getHTML(url).select("div.bui-list__description");
        for (Element facility : facilities) {
            String service = facility.select("div").attr("class", "bui-list__description").text();
            services.add(service);
            System.out.println(service);
        }
        HashMap<String, Double> ratingsMap =  new HashMap<>();
        Elements marks = getHTML(url).select("div.ee746850b6.b8eef6afe1");
        List<Double> ratings = new ArrayList<>();
        for (Element mark : marks) {
                String nota = mark.select("div").attr("class", "ee746850b6 b8eef6afe1").text();
                System.out.println(nota);
                String num = nota.replace(",", ".");
                double rating = Double.parseDouble(num);
                ratings.add(rating);
            }
        ratingsMap.put("Staff", ratings.get(0));
        ratingsMap.put("Facilities and services", ratings.get(1));
        ratingsMap.put("Cleaning", ratings.get(2));
        ratingsMap.put("Comfort", ratings.get(3));
        ratingsMap.put("Quality-price", ratings.get(4));
        ratingsMap.put("Location", ratings.get(5));
        ratingsMap.put("WiFi", ratings.get(6));
        List<Review> reviews = new ArrayList<>();
        String urlReview = "https://www.google.com/search?q=booking+reviews+gloria+palace+amadores";
        Elements urlReviews = getHTML(urlReview).select("div.Z26q7c.UK95Uc.jGGQ5e");
        String webReviews = urlReviews.select("a").attr("href");
        Elements comment = getHTML(webReviews).select("div.review_item_review_content");
        for (Element comments : comment) {
            Elements positives = comments.select("p.review_pos");
            String positive = positives.select("span").attr("itemprop", "reviewBody").text();
            System.out.println(positive);

            Elements negatives = comments.select("p.review_neg");
            String negative = negatives.select("span").attr("itemprop", "reviewBody").text();
            System.out.println(negative);
            Review review = new Review(positive, negative);
            reviews.add(review);
        }
        return new Hotel(location, services, ratingsMap, reviews);
    }
}

