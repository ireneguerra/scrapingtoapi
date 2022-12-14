package org.example;

public class Review {
    private final String positive;
    private final String negative;

    public Review(String positive, String negative) {
        this.positive = positive;
        this.negative = negative;
    }

    public String getPositive() {
        return positive;
    }

    public String getNegative() {
        return negative;
    }
}
