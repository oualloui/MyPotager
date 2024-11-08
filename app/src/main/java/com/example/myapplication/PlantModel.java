package com.example.myapplication;

public class PlantModel {

    private String id;
    private String name;
    private String description;
    private String imageUrl;
    private boolean liked;
    private String grow;
    private String water;


    public PlantModel() {
        this.name = "Pomme";
        this.description = "inserer une description";
        this.imageUrl = "http://graven.yt/plant.jpg";
        this.liked = false;
        this.id = "plant0";
        this.grow = "faible";
        this.water = "Moyenne";


    }

    public PlantModel(String id, String name, String description, String imageUrl, boolean liked) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.liked = liked;
        this.id = id;
    }

    // Getters and setters

    public String getGrow() {
        return grow;
    }

    public void setGrow(String grow) {
        this.grow = grow;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public String getId(){ return id;}

    public void setId(String id){this.id = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}
