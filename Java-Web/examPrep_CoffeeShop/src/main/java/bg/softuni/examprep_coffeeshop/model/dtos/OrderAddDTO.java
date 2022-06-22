package bg.softuni.examprep_coffeeshop.model.dtos;

import bg.softuni.examprep_coffeeshop.model.entity.Category;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderAddDTO {

    @NotBlank
    @Size(min = 3, max = 20)
    private String name;

    @NotBlank
    @Positive
    private BigDecimal price;

    @PastOrPresent
    @DateTimeFormat(pattern = "MM-dd-yyyy'T'HH:mm:ss")
    private LocalDateTime orderTime;

    @NotBlank
    private Category category;

    @Size(min = 5)
    private String description;

    public OrderAddDTO() {
    }

    public String getName() {
        return name;
    }

    public OrderAddDTO setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderAddDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public OrderAddDTO setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public OrderAddDTO setCategory(Category category) {
        this.category = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OrderAddDTO setDescription(String description) {
        this.description = description;
        return this;
    }
}
