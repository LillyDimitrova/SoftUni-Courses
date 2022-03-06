package com.softuni.training;

public class Robot implements Identifiable{

    private final String model;
    private final String id;

    public Robot(String model, String id) {
        this.model = model;
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    public String getModel() {
        return this.model;
    }
}
