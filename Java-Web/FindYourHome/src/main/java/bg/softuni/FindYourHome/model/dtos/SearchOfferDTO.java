package bg.softuni.FindYourHome.model.dtos;

public class SearchOfferDTO {

    private String City;

    private Integer minPrice;

    private Integer maxPrice;


    public String getCity() {
        return City;
    }

    public SearchOfferDTO setCity(String city) {
        City = city;
        return this;
    }

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
        return (getCity() == null || getCity().isEmpty()) &&
                minPrice == null &&
                maxPrice == null;
    }
}
