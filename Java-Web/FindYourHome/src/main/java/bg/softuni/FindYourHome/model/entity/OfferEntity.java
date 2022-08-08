package bg.softuni.FindYourHome.model.entity;

import bg.softuni.FindYourHome.model.enums.CategoryEnum;
import bg.softuni.FindYourHome.model.enums.TypeHouseEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;


@Entity
@Table(name = "offers")
public class OfferEntity extends BaseEntity{

    @Lob
    private String description;

    @ManyToOne
    private UserEntity seller;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryEnum category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeHouseEnum typeHouse;

    @ManyToOne
    private CityEntity city;

    @Column(nullable = false)
    private LocalDate yearOfConstruction;

    @Column(nullable = false)
    private BigDecimal price;


    private Instant instant;

    @Lob
    private String imageUrl;

    public OfferEntity() {
    }

    public CategoryEnum getCategory() {

        return category;
    }

    public CityEntity getCity() {
        return city;
    }

    public OfferEntity setCity(CityEntity city) {
        this.city = city;
        return this;
    }

    public OfferEntity setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }

    public TypeHouseEnum getTypeHouse() {
        return typeHouse;
    }

    public OfferEntity setTypeHouse(TypeHouseEnum typeHouse) {
        this.typeHouse = typeHouse;
        return this;
    }

    public LocalDate getYearOfConstruction() {
        return yearOfConstruction;
    }

    public OfferEntity setYearOfConstruction(LocalDate yearOfConstruction) {
        this.yearOfConstruction = yearOfConstruction;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public UserEntity getSeller() {
        return seller;
    }

    public OfferEntity setSeller(UserEntity seller) {
        this.seller = seller;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Instant getInstant() {
        return instant;
    }

    public OfferEntity setInstant(Instant instant) {
        this.instant = instant;
        return this;
    }
}
