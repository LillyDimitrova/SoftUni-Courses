package bg.softuni.security.model.entity;

import bg.softuni.security.model.enums.UserRoleEnum;


import javax.persistence.*;

@Entity
@Table(name ="user_roles")
public class UserRoleEntity extends BaseEntity{


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum userRole;



    public UserRoleEnum getUserRole() {
        return userRole;
    }

    public UserRoleEntity setUserRole(UserRoleEnum userRole) {
        this.userRole = userRole;
        return this;
    }

    @Override
    public String toString() {
        return "UserRoleEntity{" +
                ", userRole=" + userRole +
                '}';
    }
}
