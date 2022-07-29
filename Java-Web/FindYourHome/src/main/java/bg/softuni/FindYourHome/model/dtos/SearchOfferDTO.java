package bg.softuni.FindYourHome.model.dtos;

public class SearchOfferDTO {

    private Integer minPrice;

    private Integer maxPrice;

    public Integer getMinPrice() {
        return minPrice;
    }

    public SearchOfferDTO setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public SearchOfferDTO setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }

    public boolean isEmpty() {
        return
                minPrice == null &&
                maxPrice == null;
    }

    @Override
    public String toString() {
        return "SearchOfferDTO{" +
                "minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                '}';
    }
}
