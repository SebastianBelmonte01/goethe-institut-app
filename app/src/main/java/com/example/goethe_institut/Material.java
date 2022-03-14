package com.example.goethe_institut;

import android.graphics.Bitmap;

public class Material {
    private int id;
    private String type;
    private double cost;
    private int cantidad;
    private String imageDirection;
    private String description;
    private Bitmap image;

    public Material(int id, String type, double cost, int cantidad, String imageDirection, String description, Bitmap image) {
        this.id = id;
        this.type = type;
        this.cost = cost;
        this.cantidad = cantidad;
        this.imageDirection = imageDirection;
        this.description = description;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getImageDirection() {
        return imageDirection;
    }

    public void setImageDirection(String imageDirection) {
        this.imageDirection = imageDirection;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", cost=" + cost +
                ", cantidad=" + cantidad +
                ", imageDirection='" + imageDirection + '\'' +
                '}';
    }
}
