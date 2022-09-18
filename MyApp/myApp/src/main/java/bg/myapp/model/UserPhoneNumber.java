package bg.myapp.model;

import javax.persistence.*;

@Entity
@Table(name = "user_phone_number")
public class UserPhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phoneNumber;

    @OneToOne
    private Account user;

    @OneToOne
    private PhoneType phoneType;

    public UserPhoneNumber() {
    }

    public Long getId() {
        return id;
    }

    public UserPhoneNumber setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserPhoneNumber setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Account getUser() {
        return user;
    }

    public UserPhoneNumber setUser(Account user) {
        this.user = user;
        return this;
    }

    public PhoneType getPhoneType() {
        return phoneType;
    }

    public UserPhoneNumber setPhoneType(PhoneType phoneType) {
        this.phoneType = phoneType;
        return this;
    }
}
