package com.example.pathfinder.model.entity;

import com.example.pathfinder.model.enums.CategoriesEnum;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private CategoriesEnum name;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    public Category() {
    }

    public CategoriesEnum getName() {
        return name;
    }

    public Category setName(CategoriesEnum name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Category setDescription(String description) {
        this.description = description;
        return this;
    }
}
