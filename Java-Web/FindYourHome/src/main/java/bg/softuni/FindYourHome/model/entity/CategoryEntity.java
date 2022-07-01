package bg.softuni.FindYourHome.model.entity;

import bg.softuni.FindYourHome.model.enums.CategoryEnum;
import org.hibernate.annotations.Columns;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity{

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    public CategoryEntity() {
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public CategoryEntity setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }
}
