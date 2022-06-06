package bg.softuni.housefinder.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    private String firstName;
    private String lastName;
    private boolean isActive;
}
