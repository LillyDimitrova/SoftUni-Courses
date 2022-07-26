package bg.softuni.FindYourHome.model.dtos;

import bg.softuni.FindYourHome.model.entity.UserEntity;
import bg.softuni.FindYourHome.model.enums.CategoryEnum;
import bg.softuni.FindYourHome.model.enums.TypeHouseEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CreateOfferDTO {

    @NotBlank(message = "Please enter a description!")
    private String description;
    @NotNull(message = "Please choose type!")
    private TypeHouseEnum type;
    @NotNull(message = "Please choose category!")
    private CategoryEnum category;

    @NotEmpty(message = "Add a photo!")
    private String imageUrl;
    @NotNull(message = "Price must be positive number!")
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

    public BigDecimal getPrice() {
        return price;
    }

    public CreateOfferDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public CreateOfferDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
