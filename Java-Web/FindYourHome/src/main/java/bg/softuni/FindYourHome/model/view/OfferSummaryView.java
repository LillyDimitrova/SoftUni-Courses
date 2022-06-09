package bg.softuni.FindYourHome.model.view;

import bg.softuni.FindYourHome.model.entity.enums.CategoryEnum;
import bg.softuni.FindYourHome.model.entity.enums.TypeEnum;

public class OfferSummaryView {

    private String description;

    private String imageUrl;

    private int price;

    private TypeEnum type;

    private CategoryEnum category;

    private Integer yearOfConstruction;

    public String getDescription() {
        return description;
    }

    public OfferSummaryView setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferSummaryView setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public OfferSummaryView setPrice(int price) {
        this.price = price;
        return this;
    }

    public TypeEnum getType() {
        return type;
    }

    public OfferSummaryView setType(TypeEnum type) {
        this.type = type;
        return this;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public OfferSummaryView setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }

    public Integer getYearOfConstruction() {
        return yearOfConstruction;
    }

    public OfferSummaryView setYearOfConstruction(Integer yearOfConstruction) {
        this.yearOfConstruction = yearOfConstruction;
        return this;
    }
}
