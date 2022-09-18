package bg.myapp.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Date create_at;

    @Column(nullable = false)
    private Date modified_at;



    public Account() {
    }

    public Long getId() {
        return id;
    }

    public Account setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Account setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Account setPassword(String password) {
        this.password = password;
        return this;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public Account setCreate_at(Date create_at) {
        this.create_at = create_at;
        return this;
    }

    public Date getModified_at() {
        return modified_at;
    }

    public Account setModified_at(Date modified_at) {
        this.modified_at = modified_at;
        return this;
    }
}
