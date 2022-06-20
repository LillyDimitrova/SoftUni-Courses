package bg.softuni.battleShips_ExamPreparation.model.entity;

import bg.softuni.battleShips_ExamPreparation.model.enums.ShipType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity{

    @Enumerated
    @Column(unique = true, nullable = false)
    private ShipType name;

    @Column(columnDefinition = "text")
    private String description;

    public CategoryEntity() {

    }

    public ShipType getName() {
        return name;
    }

    public CategoryEntity setName(ShipType name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CategoryEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
