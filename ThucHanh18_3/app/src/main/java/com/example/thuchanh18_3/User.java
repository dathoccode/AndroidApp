package com.example.thuchanh18_3;

public class User {
    int id;
    String name, address;
    double rating;

    public User(String address, int id, String name, double rating) {
        this.address = address;
        this.id = id;
        this.name = name;
        this.rating = rating;
    }
}
