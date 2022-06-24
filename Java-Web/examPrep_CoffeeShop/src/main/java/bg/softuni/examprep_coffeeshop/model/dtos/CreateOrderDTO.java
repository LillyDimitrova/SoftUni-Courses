package bg.softuni.examprep_coffeeshop.model.dtos;

import bg.softuni.examprep_coffeeshop.model.enums.CategoryNameEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CreateOrderDTO {


    @Size(min = 3, max = 20)
    private String name;


    @Positive
    private BigDecimal price;

    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime orderTime;


    private CategoryNameEnum category;

    @Size(min = 5)
    private String description;

    public CreateOrderDTO() {
    }

    public String getName() {
        return name;
    }

    public CreateOrderDTO setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CreateOrderDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public CreateOrderDTO setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
        return this;
    }

    public CategoryNameEnum getCategory() {
        return category;
    }

    public CreateOrderDTO setCategory(CategoryNameEnum category) {
        this.category = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CreateOrderDTO setDescription(String description) {
        this.description = description;
        return this;
    }
}
