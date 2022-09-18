package bg.myapp.model;

import javax.persistence.*;

@Entity
@Table(name = "user_email")
public class UserEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String email;

    @OneToOne
    private Account user;

    public UserEmail() {
    }

    public Long getId() {
        return id;
    }

    public UserEmail setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEmail setEmail(String email) {
        this.email = email;
        return this;
    }

    public Account getUser() {
        return user;
    }

    public UserEmail setUser(Account user) {
        this.user = user;
        return this;
    }
}
