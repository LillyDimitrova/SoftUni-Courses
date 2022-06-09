package bg.softuni.FindYourHome.model.entity;

import bg.softuni.FindYourHome.model.entity.enums.CategoryEnum;

import javax.persistence.*;

@Entity
@Table(name = "offers")
public class OfferEntity extends BaseEntity{

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;


    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int year;

    @ManyToOne
    private HouseEntity typeHouse;

    @ManyToOne
    private UserEntity seller;

    public String getDescription() {
        return description;
    }

    public OfferEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public OfferEntity setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public OfferEntity setPrice(int price) {
        this.price = price;
        return this;
    }

    public int getYear() {
        return year;
    }

    public OfferEntity setYear(int year) {
        this.year = year;
        return this;
    }

    public HouseEntity getTypeHouse() {
        return typeHouse;
    }

    public OfferEntity setTypeHouse(HouseEntity typeHouse) {
        this.typeHouse = typeHouse;
        return this;
    }

    public UserEntity getSeller() {
        return seller;
    }

    public OfferEntity setSeller(UserEntity seller) {
        this.seller = seller;
        return this;
    }
}
