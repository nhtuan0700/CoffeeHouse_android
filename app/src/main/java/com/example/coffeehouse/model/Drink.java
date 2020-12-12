package com.example.coffeehouse.model;

public class Drink {
    private String id;
    private String name;
    private int price;
    private String image;
    private String description;
    private int favourite;
    private String category;

    public Drink() {
    }

    public Drink(String id, String name, int price, String image, String description, int favourite, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
        this.favourite = favourite;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public int getFavourite() {
        return favourite;
    }

    public String getCategory() {
        return category;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFavourite(int favourite) {
        this.favourite = favourite;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
