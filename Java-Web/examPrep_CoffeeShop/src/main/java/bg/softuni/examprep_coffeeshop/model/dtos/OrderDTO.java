package bg.softuni.examprep_coffeeshop.model.dtos;

import bg.softuni.examprep_coffeeshop.model.entity.Category;
import bg.softuni.examprep_coffeeshop.model.entity.Order;

import java.math.BigDecimal;

public class OrderDTO {

    private Long id;
    private String name;
    private BigDecimal price;
    private Category category;

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.name = order.getName();
        this.price = order.getPrice();
        this.category = order.getCategory();
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }
}
