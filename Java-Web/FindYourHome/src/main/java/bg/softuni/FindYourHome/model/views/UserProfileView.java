package bg.softuni.FindYourHome.model.views;


import bg.softuni.FindYourHome.model.user.CurrentUserDetails;

public class UserProfileView {


    private String username;
    private String email;
    private String firstName;
    private String lastName;


    public UserProfileView() {
    }

    public UserProfileView(String username, String email, String firstName, String lastName) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserProfileView setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserProfileView setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserProfileView setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserProfileView setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }



}
