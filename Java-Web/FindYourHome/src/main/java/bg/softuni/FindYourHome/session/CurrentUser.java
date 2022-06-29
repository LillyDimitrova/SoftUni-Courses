package bg.softuni.FindYourHome.session;

import bg.softuni.FindYourHome.model.entity.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashSet;
import java.util.Set;

@Component
@SessionScope
public class CurrentUser {

    private long id;
    private String username;

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


}
