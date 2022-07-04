package bg.softuni.FindYourHome.model.entity;

import bg.softuni.FindYourHome.model.enums.TypeHouseEnum;

import javax.persistence.*;

@Entity
@Table
public class TypeHouseEntity extends BaseEntity{
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeHouseEnum type;

    public TypeHouseEntity() {
    }

    public TypeHouseEnum getType() {
        return type;
    }

    public TypeHouseEntity setType(TypeHouseEnum type) {
        this.type = type;
        return this;
    }
}
