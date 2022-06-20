package bg.softuni.battleShips_ExamPreparation.session;

import bg.softuni.battleShips_ExamPreparation.model.entity.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class LoggedUser {

    private long id;
    private String fullName;

    public void login(UserEntity user) {
        this.id = user.getId();
        this.fullName = user.getFullName();
    }

    public void logout(){
        this.id = 0;
        this.fullName = null;
    }

    public Long getId() {
        return id;
    }

    public LoggedUser setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public LoggedUser setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }
}
