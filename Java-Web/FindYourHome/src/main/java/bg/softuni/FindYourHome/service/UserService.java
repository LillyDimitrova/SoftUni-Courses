package bg.softuni.FindYourHome.service;

import bg.softuni.FindYourHome.model.dtos.UserLoginDTO;
import bg.softuni.FindYourHome.model.dtos.UserRegistrationDTO;
import bg.softuni.FindYourHome.model.entity.UserEntity;
import bg.softuni.FindYourHome.repository.UserRepository;
import bg.softuni.FindYourHome.session.CurrentUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, CurrentUser currentUser, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.passwordEncoder = passwordEncoder;
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
                setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword())).
                setUsername(userRegistrationDTO.getUsername()).
                setFirstName(userRegistrationDTO.getFirstName()).
                setLastName(userRegistrationDTO.getLastName()).
                setEmail(userRegistrationDTO.getEmail());
        userRepository.save(user);

        return true;
    }

    public boolean login(UserLoginDTO userLoginDTO) {
        Optional<UserEntity> user = userRepository.findByUsername(userLoginDTO.getUsername());
        if (!user.isPresent()) {
            return false;
        }

        String rawPassword = userLoginDTO.getPassword();
        String encodedPassword = user.get().getPassword();

        this.currentUser.login(user.get());
        boolean success = passwordEncoder.
                matches(rawPassword, encodedPassword);
        if (success) {
            this.currentUser.login(user.get());

        } else {
            logout();
        }
        return success;
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
