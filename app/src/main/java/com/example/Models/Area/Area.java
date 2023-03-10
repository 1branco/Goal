package com.example.Models.Area;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Area implements Serializable {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    public Area(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public String getId() { return id;}
}
