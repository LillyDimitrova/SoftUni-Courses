package bg.softuni.FindYourHome.model.dtos;

import bg.softuni.FindYourHome.model.validator.FieldMatch;
import bg.softuni.FindYourHome.model.validator.UniqueUserEmail;
import bg.softuni.FindYourHome.model.validator.UniqueUsername;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.*;

@FieldMatch(
        first = "password",
        second = "confirmPassword",
        message = "Passwords do not match."
)

public class UserRegistrationDTO {

    @NotBlank(message = "Username should be provided")
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters")
    @UniqueUsername(message = "Username should be unique.")
    private String username;
    @NotBlank
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters")
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters")
    private String lastName;

    @NotBlank(message = "User email should be provided")
    @Email(message = "User email should be valid. ")
    @UniqueUserEmail(message = "User email should be unique.")
    private String email;
    @NotBlank
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters")
    private String password;
    @NotBlank
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters")
    private String confirmPassword;

    public UserRegistrationDTO() {
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegistrationDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegistrationDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserRegistrationDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegistrationDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegistrationDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
