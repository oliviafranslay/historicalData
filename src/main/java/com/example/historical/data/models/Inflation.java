package com.example.historical.data.models;

public class Inflation {
    private int id;
    private String month;
    private int year;
    private double inflationIndex;

    public Inflation() {

    }

    public Inflation(int id, String month, int year, double inflationIndex) {
        this.id = id;
        this.month = month;
        this.year = year;
        this.inflationIndex = inflationIndex;

    }
}
