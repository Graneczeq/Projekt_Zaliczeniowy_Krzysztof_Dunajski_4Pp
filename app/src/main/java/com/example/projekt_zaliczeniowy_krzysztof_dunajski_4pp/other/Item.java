package com.example.projekt_zaliczeniowy_krzysztof_dunajski_4pp.other;

public class Item {
    private String name;
    private final String price, description;
    private final int image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getImage() {
        return image;
    }

    public Item(String name, String price, String description, int image) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
    }
}
