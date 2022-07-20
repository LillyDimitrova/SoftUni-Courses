package bg.softuni.FindYourHome.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{

    @Column(unique = true,nullable = false)
    private String username;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<RoleEntity> roles = new ArrayList<>();

    @Column(unique = true,nullable = false)
    private String email;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<OfferEntity> offers = new ArrayList<>();
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String password;

    public UserEntity() {
    }

    public List<OfferEntity> getOffers() {
        return offers;
    }

    public UserEntity setOffers(List<OfferEntity> offers) {
        this.offers = offers;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(List<RoleEntity> roles) {
        this.roles = roles;
        return this;
    }
    public UserEntity addRole(RoleEntity userRole) {
        this.roles.add(userRole);
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "username='" + username + '\'' +
                ", roles=" + roles +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
