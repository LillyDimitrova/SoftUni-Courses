package bg.softuni.FindYourHome.model.entity;

import bg.softuni.FindYourHome.model.enums.RoleEnum;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity{


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum role;


    public RoleEnum getRole() {
        return role;
    }

    public RoleEntity setRole(RoleEnum role) {
        this.role = role;
        return this;
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "role=" + role +
                '}';
    }
}
