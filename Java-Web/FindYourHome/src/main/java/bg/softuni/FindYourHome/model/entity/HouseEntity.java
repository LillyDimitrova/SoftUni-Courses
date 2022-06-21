package bg.softuni.FindYourHome.model.entity;

import bg.softuni.FindYourHome.model.enums.CategoryEnum;
import bg.softuni.FindYourHome.model.enums.TypeHouse;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "houses")
public class HouseEntity extends BaseEntity{

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeHouse type;

    @Column(nullable = false)
    private LocalDate yearOfConstruction;

    public HouseEntity() {
    }

    public String getDescription() {
        return description;
    }

    public HouseEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public HouseEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public HouseEntity setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }

    public TypeHouse getType() {
        return type;
    }

    public HouseEntity setType(TypeHouse type) {
        this.type = type;
        return this;
    }

    public LocalDate getYearOfConstruction() {
        return yearOfConstruction;
    }

    public HouseEntity setYearOfConstruction(LocalDate yearOfConstruction) {
        this.yearOfConstruction = yearOfConstruction;
        return this;
    }
}
