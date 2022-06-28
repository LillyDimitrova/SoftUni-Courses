package bg.softuni.exam.session;

import bg.softuni.exam.model.dtos.SongDTO;
import bg.softuni.exam.model.entity.BaseEntity;
import bg.softuni.exam.model.entity.SongEntity;
import bg.softuni.exam.model.entity.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashSet;
import java.util.Set;

@Component
@SessionScope
public class CurrentUser {

    private long id;
    private String username;
    private final Set<SongEntity> currentPlayList;

    public CurrentUser(Set<SongEntity> currentPlayList) {
        this.currentPlayList = new HashSet<>();
    }

    public void login(UserEntity user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }


    public void logout(){
        this.id = 0;
        this.username = null;
    }

    public long getId() {
        return id;
    }

    public CurrentUser setId(long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public CurrentUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public void add(SongEntity song) {
        currentPlayList.add(song);
    }

    public Set<SongEntity> getCurrentPlayList() {
        return currentPlayList;
    }

    public void removeAll() {
        currentPlayList.clear();
    }
}
