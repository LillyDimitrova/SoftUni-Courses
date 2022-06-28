package bg.softuni.exam.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{

    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(unique = true,nullable = false)
    private String email;

    @OneToMany(mappedBy = "user")
    private Set<SongEntity<BaseEntity>> playList;

    public UserEntity() {
    }

    public Set<SongEntity<BaseEntity>> getPlayList() {
        return playList;
    }

    public UserEntity setPlayList(Set<SongEntity<BaseEntity>> playList) {
        this.playList = playList;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }
}
