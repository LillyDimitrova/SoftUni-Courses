package bg.softuni.FindYourHome.model.dtos;

import bg.softuni.FindYourHome.model.entity.RoleEntity;
import bg.softuni.FindYourHome.model.entity.UserEntity;
import bg.softuni.FindYourHome.model.enums.RoleEnum;

public class UserDetailDTO {


    private Long id;
    private String username;
    private RoleEntity role;
    private String email;
    private String firstName;
    private String lastName;
    private Integer countOfOffers;

    public UserDetailDTO() {

    }

    public UserDetailDTO(UserEntity user) {
        this.username = user.getUsername();
        this.role = user.getRoles().get(0);
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

    public RoleEntity getRole() {
        return role;
    }

    public UserDetailDTO setRole(RoleEntity role) {
        this.role = role;
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
