package com.example.coffeehouse.model;

public class News {
    private int id;
    private String title;
    private String time;
    private String image;
    private String description;
    private String category;

    public News() {
    }

    public News(int id, String title, String time, String image, String description, String category) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.image = image;
        this.description = description;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
