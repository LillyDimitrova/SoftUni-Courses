package bg.softuni.FindYourHome.model.dtos;

import bg.softuni.FindYourHome.model.entity.UserEntity;
import bg.softuni.FindYourHome.model.enums.CategoryEnum;
import bg.softuni.FindYourHome.model.enums.TypeHouseEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CreateOfferDTO {

    @NotNull
    private String description;
    @NotNull
    private TypeHouseEnum type;
    @NotNull
    private CategoryEnum category;

    @NotNull
    private UserEntity seller;

    @NotNull
    @Positive
    private BigDecimal price;

    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate yearOfConstruction;

    public String getDescription() {
        return description;
    }

    public CreateOfferDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public TypeHouseEnum getType() {
        return type;
    }

    public CreateOfferDTO setType(TypeHouseEnum type) {
        this.type = type;
        return this;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public CreateOfferDTO setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }

    public UserEntity getSeller() {
        return seller;
    }

    public CreateOfferDTO setSeller(UserEntity seller) {
        this.seller = seller;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CreateOfferDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public CreateOfferDTO() {
    }

    public LocalDate getYearOfConstruction() {
        return yearOfConstruction;
    }

    public CreateOfferDTO setYearOfConstruction(LocalDate yearOfConstruction) {
        this.yearOfConstruction = yearOfConstruction;
        return this;
    }
}
