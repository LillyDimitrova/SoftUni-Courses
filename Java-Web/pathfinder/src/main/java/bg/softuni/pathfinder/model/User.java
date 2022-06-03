package bg.softuni.pathfinder.model;

import bg.softuni.pathfinder.model.enums.Level;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class BaseUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String email;

    @Column(name = "full_name")
    private String fullName;

    private int age;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @Enumerated(EnumType.STRING)
    private Level level;

    public BaseUser() {
        this.roles = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public BaseUser setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public BaseUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public BaseUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public BaseUser setEmail(String email) {
        this.email = email;
        return this;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public BaseUser setRoles(Set<Role> roles) {
        this.roles = roles;
        return this;
    }

    public Level getLevel() {
        return level;
    }

    public BaseUser setLevel(Level level) {
        this.level = level;
        return this;
    }
}
