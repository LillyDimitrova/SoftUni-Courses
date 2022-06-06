package bg.softuni.FindYourHome.service;

import bg.softuni.FindYourHome.model.service.UserLoginServiceModel;

public interface UserService {

    void initializeUsersAndRoles();

    boolean login(UserLoginServiceModel loginServiceModel);

    void logout();
}
