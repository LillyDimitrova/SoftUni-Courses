package bg.softuni.examprep_coffeeshop.model.dtos;

import bg.softuni.examprep_coffeeshop.model.entity.Category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class AddOrderDTO {

    @NotBlank
    @Size(min = 3, max = 20)
    private String name;

    @NotBlank
    @Positive
    private Integer price;

    @PastOrPresent
    private LocalDateTime orderTime;

    @NotBlank
    private Category category;

    @Size(min = 5)
    private String description;

    public AddOrderDTO() {
    }

    public String getName() {
        return name;
    }

    public AddOrderDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public AddOrderDTO setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public AddOrderDTO setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public AddOrderDTO setCategory(Category category) {
        this.category = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddOrderDTO setDescription(String description) {
        this.description = description;
        return this;
    }
}
