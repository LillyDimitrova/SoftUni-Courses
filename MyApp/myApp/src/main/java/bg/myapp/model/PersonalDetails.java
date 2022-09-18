package bg.myapp.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "personal_details")
public class PersonalDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date date_of_birth;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @OneToOne
    private Account user;

    @Column
    private String interests;

    public PersonalDetails() {
    }

    public Long getId() {
        return id;
    }

    public PersonalDetails setId(Long id) {
        this.id = id;
        return this;
    }


    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public PersonalDetails setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public PersonalDetails setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public PersonalDetails setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getInterests() {
        return interests;
    }

    public PersonalDetails setInterests(String interests) {
        this.interests = interests;
        return this;
    }

    public Account getUser() {
        return user;
    }

    public PersonalDetails setUser(Account user) {
        this.user = user;
        return this;
    }
}
