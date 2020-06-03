package com.myapplicationdev.android.rideforreward;

import java.io.Serializable;

public class RewardsClass implements Serializable {
    private String Name;
    private String Description;
    private int img;
    private int points;

    public RewardsClass(String name, String description, int img, int points) {
        Name = name;
        Description = description;
        this.img = img;
        this.points = points;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
