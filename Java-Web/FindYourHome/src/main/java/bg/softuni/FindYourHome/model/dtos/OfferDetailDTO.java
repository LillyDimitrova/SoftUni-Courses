package bg.softuni.FindYourHome.model.dtos;


import bg.softuni.FindYourHome.model.enums.CategoryEnum;
import bg.softuni.FindYourHome.model.enums.TypeHouseEnum;
import java.math.BigDecimal;
import java.time.LocalDate;

public class OfferDetailDTO {


    private String description;
    private CategoryEnum category;
    private TypeHouseEnum typeHouse;
    private LocalDate yearOfConstruction;
    private BigDecimal price;
    private String imageUrl;

    public OfferDetailDTO() {
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
}
