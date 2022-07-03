package bg.softuni.FindYourHome.service;

import bg.softuni.FindYourHome.model.dtos.UserLoginDTO;
import bg.softuni.FindYourHome.model.dtos.UserRegistrationDTO;
import bg.softuni.FindYourHome.model.entity.RoleEntity;
import bg.softuni.FindYourHome.model.entity.UserEntity;
import bg.softuni.FindYourHome.model.enums.RoleEnum;
import bg.softuni.FindYourHome.repository.UserRepository;
import bg.softuni.FindYourHome.session.CurrentUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final CurrentUser currentUser;

    public UserService(UserRepository userRepository, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
    }

    public boolean register(UserRegistrationDTO userRegistrationDTO) {
        if (!userRegistrationDTO.getPassword().equals(userRegistrationDTO.getConfirmPassword())) {
            return false;
        }

        Optional<UserEntity> byEmail = userRepository.findByEmail(userRegistrationDTO.getEmail());
        if (byEmail.isPresent()) {
            return false;
        }

        Optional<UserEntity> byUsername = userRepository.findByUsername(userRegistrationDTO.getUsername());
        if (byUsername.isPresent()) {
            return false;
        }

        UserEntity user = new UserEntity();
        user.setEmail(userRegistrationDTO.getEmail()).
                setPassword(userRegistrationDTO.getPassword()).
                setUsername(userRegistrationDTO.getUsername()).
                setFirstName(userRegistrationDTO.getFirstName()).
                setLastName(userRegistrationDTO.getLastName()).
                setEmail(userRegistrationDTO.getEmail());
        userRepository.save(user);

        return true;
    }

    public boolean login(UserLoginDTO userLoginDTO) {
        Optional<UserEntity> user = userRepository.findByUsernameAndPassword(userLoginDTO.getUsername(), userLoginDTO.getPassword());
        if (!user.isPresent()) {
            return false;
        }
        this.currentUser.login(user.get());

        return true;
    }
    public void logout() {
        this.currentUser.logout();
    }

    public boolean isLoggedIn() {
        return this.currentUser.getId() > 0;
    }

    public long getLoggedUserId() {
        return this.currentUser.getId();
    }
}
