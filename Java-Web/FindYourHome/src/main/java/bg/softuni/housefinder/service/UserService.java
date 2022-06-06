package bg.softuni.housefinder.service;

import bg.softuni.housefinder.model.service.UserLoginServiceModel;

public interface UserService {

    void initializeUsersAndRoles();

    boolean login(UserLoginServiceModel loginServiceModel);

    void logout();
}
