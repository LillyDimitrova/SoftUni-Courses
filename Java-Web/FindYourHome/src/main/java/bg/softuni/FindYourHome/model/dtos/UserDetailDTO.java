package bg.softuni.FindYourHome.model.dtos;

import bg.softuni.FindYourHome.model.entity.RoleEntity;
import bg.softuni.FindYourHome.model.entity.UserEntity;
import bg.softuni.FindYourHome.model.enums.RoleEnum;

import java.util.List;

public class UserDetailDTO {


    private Long id;
    private String username;
    private List<RoleEntity> roles;
    private String email;
    private String firstName;
    private String lastName;
    private Integer countOfOffers;

    public UserDetailDTO() {

    }

    public UserDetailDTO(UserEntity user) {
        this.username = user.getUsername();
        this.roles = user.getRoles();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.countOfOffers = user.getOffers().size();
        this.id = user.getId();
    }

    public Long getId() {
        return id;
    }

    public UserDetailDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getCountOfOffers() {
        return countOfOffers;
    }

    public UserDetailDTO setCountOfOffers(Integer countOfOffers) {
        this.countOfOffers = countOfOffers;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDetailDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public UserDetailDTO setRoles(List<RoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDetailDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDetailDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDetailDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
