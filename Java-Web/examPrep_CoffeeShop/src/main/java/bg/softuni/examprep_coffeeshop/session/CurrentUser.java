package bg.softuni.examprep_coffeeshop.session;

import bg.softuni.examprep_coffeeshop.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {

    private long id;
    private String username;

    public CurrentUser() {}

    public long getId() {
        return id;
    }

    public void login(User user) {
        this.id = user.getId();
        this.username = getUsername();
    }

    public void logout(){
        this.id = 0;
        this.username = null;
    }
    public CurrentUser setId(Long id) {
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
