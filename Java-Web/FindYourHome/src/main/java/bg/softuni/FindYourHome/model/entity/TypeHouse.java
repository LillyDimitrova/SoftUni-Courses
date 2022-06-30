package bg.softuni.FindYourHome.model.entity;

import bg.softuni.FindYourHome.model.enums.TypeHouseEnum;
import org.hibernate.annotations.Columns;

import javax.persistence.*;

@Entity
@Table
public class TypeHouse extends BaseEntity{
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeHouseEnum type;

    public TypeHouseEnum getType() {
        return type;
    }

    public TypeHouse setType(TypeHouseEnum type) {
        this.type = type;
        return this;
    }
}
