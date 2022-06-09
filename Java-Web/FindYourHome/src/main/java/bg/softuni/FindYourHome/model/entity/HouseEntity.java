package bg.softuni.FindYourHome.model.entity;

import bg.softuni.FindYourHome.model.entity.enums.CategoryEnum;
import bg.softuni.FindYourHome.model.entity.enums.TypeEnum;

import javax.persistence.*;

@Entity
@Table(name = "houses")
public class HouseEntity extends BaseEntity{
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeEnum type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryEnum category;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private Integer yearOfConstruction;

    public CategoryEnum getCategory() {
        return category;
    }

    public HouseEntity setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }

    public TypeEnum getType() {
        return type;
    }

    public HouseEntity setType(TypeEnum type) {
        this.type = type;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public HouseEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getYearOfConstruction() {
        return yearOfConstruction;
    }

    public HouseEntity setYearOfConstruction(Integer yearOfConstruction) {
        this.yearOfConstruction = yearOfConstruction;
        return this;
    }
}
