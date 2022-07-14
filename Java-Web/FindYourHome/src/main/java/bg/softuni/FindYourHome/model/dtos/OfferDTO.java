package bg.softuni.FindYourHome.model.dtos;

import bg.softuni.FindYourHome.model.entity.OfferEntity;

import java.math.BigDecimal;

public class OfferDTO {

    private String description;
    private BigDecimal price;
    private String imageUrl;


    public OfferDTO(OfferEntity offer) {
        this.description = offer.getDescription();
        this.price = offer.getPrice();
        this.imageUrl = offer.getImageUrl();
    }

    public String getDescription() {
        return description;
    }

    public OfferDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
