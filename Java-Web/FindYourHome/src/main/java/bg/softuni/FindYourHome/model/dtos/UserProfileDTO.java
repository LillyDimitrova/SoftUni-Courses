package bg.softuni.FindYourHome.model.dtos;

public class UserProfileDTO {


    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private int countOfOffers;


    public UserProfileDTO() {
    }

    public UserProfileDTO(String username, String email, String firstName, String lastName, int countOfOffers) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.countOfOffers = countOfOffers;
    }

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }
    public UserProfileDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserProfileDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserProfileDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserProfileDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public int getCountOfOffers() {
        return countOfOffers;
    }

    public UserProfileDTO setCountOfOffers(int countOfOffers) {
        this.countOfOffers = countOfOffers;
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
