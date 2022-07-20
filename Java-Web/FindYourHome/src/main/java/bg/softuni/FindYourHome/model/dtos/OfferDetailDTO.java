package bg.softuni.FindYourHome.model.dtos;


import bg.softuni.FindYourHome.model.entity.UserEntity;
import bg.softuni.FindYourHome.model.enums.CategoryEnum;
import bg.softuni.FindYourHome.model.enums.TypeHouseEnum;
import java.math.BigDecimal;
import java.time.LocalDate;

public class OfferDetailDTO {


    private Long id;
    private String description;
    private CategoryEnum category;
    private TypeHouseEnum typeHouse;
    private LocalDate yearOfConstruction;
    private BigDecimal price;
    private String imageUrl;

    private UserEntity seller;




    public OfferDetailDTO() {
    }

    public UserEntity getSeller() {
        return seller;
    }

    public OfferDetailDTO setSeller(UserEntity seller) {
        this.seller = seller;
        return this;
    }

    public Long getId() {
        return id;
    }

    public OfferDetailDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public TypeHouseEnum getTypeHouse() {
        return typeHouse;
    }

    public LocalDate getYearOfConstruction() {
        return yearOfConstruction;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferDetailDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public OfferDetailDTO setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }

    public OfferDetailDTO setTypeHouse(TypeHouseEnum typeHouse) {
        this.typeHouse = typeHouse;
        return this;
    }

    public OfferDetailDTO setYearOfConstruction(LocalDate yearOfConstruction) {
        this.yearOfConstruction = yearOfConstruction;
        return this;
    }

    public OfferDetailDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public OfferDetailDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
