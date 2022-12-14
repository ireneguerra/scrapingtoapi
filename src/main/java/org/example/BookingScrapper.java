package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookingScrapper{
    public static Document getHTML(String url){
        Document html = null;
        try{
            html = Jsoup.connect(url).get();
        }catch (Exception e){
            System.out.println("Error al obtener el código HTML");
        }
        return html;
    }

    public static String getWebUrl(String name){
        String url = "https://www.google.com/search?q=booking+" + name;
        System.out.println(url);
        Elements urls = getHTML(url).select("div.Z26q7c.UK95Uc.jGGQ5e.VGXe8");
        String webUrl = urls.select("a").attr("href");
        return webUrl;
    }

    public static String getTelephone(String name){
        String url = "https://www.google.com/search?q=kayak+" + name;
        Elements urls = getHTML(url).select("div.Z26q7c.UK95Uc.jGGQ5e.VGXe8");
        String webUrl = urls.select("a").attr("href");
        Elements numbers = getHTML(webUrl).select("span.r9uX-phone");
        String telephone = numbers.select("span").attr("class", "r9uX-phone").text();
        System.out.println(telephone);
        return telephone;

    }

    public static Hotel scrapHotel(String name){
        String url = getWebUrl(name);
        Elements locations = getHTML(url).select("span.hp_address_subtitle.js-hp_address_subtitle.jq_tooltip");
        String address = locations.select("span").attr("title aria-describedby", "tooltip-1").text();
        System.out.println(address);
        Elements coordinates = getHTML(url).select("div.k2-hp--gallery-header.bui-grid__column.bui-grid__column-9");
        String coordinate = coordinates.select("a").attr("data-atlas-latlng");
        System.out.println(coordinate);
        String telephone = getTelephone(name);
        return new Hotel(telephone, new Location(address, coordinate));
    }
    public static HashMap<String, List<String>> scrapServices(String name){
        String url = getWebUrl(name);
        HashMap<String, List<String>> servicesMap = new HashMap<>();
        Elements categories = getHTML(url).select("div.hotel-facilities-group");
        for (Element category : categories) {
            List<String> services = new ArrayList<>();
            Elements titles = category.select("div.bui-title__text.hotel-facilities-group__title-text");
            String title = titles.select("div").attr("class", "bui-title__text hotel-facilities-group__title-text").text();
            System.out.println(title);
            Elements facilities = category.select("div.bui-list__description");
            for (Element facility : facilities) {
                String service = facility.select("div").attr("class", "bui-list__description").text();
                services.add(service);
                //System.out.println(service);
            }
            servicesMap.put(title, services);

        }
        return servicesMap;
    }
    public static List<Review> scrapComments(String name){
        List<Review> reviews = new ArrayList<>();
        String urlReview =  "https://www.google.com/search?q=booking+reviews+" + name;
        Elements urlReviews = getHTML(urlReview).select("div.Z26q7c.UK95Uc.jGGQ5e");
        String url = urlReviews.select("a").attr("href");
        Elements comments = getHTML(url).select("div.review_item_review");
        for (Element comment : comments) {
            Elements titles = comment.select("div.review_item_header_content");
            String title = titles.select("span").attr("itemprop", "name").text();
            Elements positives = comment.select("p.review_pos");
            String positive = positives.select("span").attr("itemprop", "reviewBody").text();
            System.out.println(positive);
            Elements negatives = comment.select("p.review_neg");
            String negative = negatives.select("span").attr("itemprop", "reviewBody").text();
            System.out.println(negative);
            Review review = new Review(title, positive, negative);
            reviews.add(review);
        }
        return reviews;
    }
    public static HashMap<String, Double> scrapRatings(String name){
        String url = getWebUrl(name);
        HashMap<String, Double> ratingsMap =  new HashMap<>();
        Elements marks = getHTML(url).select("div.a1b3f50dcd.b2fe1a41c3.a1f3ecff04.e187349485.d19ba76520");
        for (Element mark : marks) {
            Elements category = mark.select("div.b1e6dd8416.aacd9d0b0a");
            String categoryName = category.select("span").attr("class", "d6d4671780").text();
            Elements puntuation = mark.select("div.ee746850b6.b8eef6afe1");
            String nota = puntuation.select("div").attr("class", "ee746850b6 b8eef6afe1").text();
            System.out.println(nota);
            String num = nota.replace(",", ".");
            double rating = Double.parseDouble(num);
            ratingsMap.put(categoryName, rating);
        }


        return ratingsMap;
    }
}
