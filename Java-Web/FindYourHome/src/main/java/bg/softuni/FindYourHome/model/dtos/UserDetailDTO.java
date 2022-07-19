package bg.softuni.FindYourHome.model.dtos;

import bg.softuni.FindYourHome.model.entity.UserEntity;

public class UserDetailDTO {


    private String username;
    private String role;
    private String email;
    private String firstName;
    private String lastName;

    public UserDetailDTO() {

    }

    public UserDetailDTO(UserEntity user) {
        this.username = user.getUsername();
        this.role = user.getRoles().get(0).toString();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }

    public String getUsername() {
        return username;
    }

    public UserDetailDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getRole() {
        return role;
    }

    public UserDetailDTO setRole(String role) {
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
