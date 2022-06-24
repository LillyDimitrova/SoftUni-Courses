package bg.softuni.battleShips_ExamPreparation.service;

import bg.softuni.battleShips_ExamPreparation.model.dto.UserLoginDTO;
import bg.softuni.battleShips_ExamPreparation.model.dto.UserRegistrationDTO;
import bg.softuni.battleShips_ExamPreparation.model.entity.UserEntity;
import bg.softuni.battleShips_ExamPreparation.repository.UserRepository;
import bg.softuni.battleShips_ExamPreparation.session.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private LoggedUser loggedUser;

    public AuthService(UserRepository userRepository, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
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
            setFullName(userRegistrationDTO.getFullName()).
            setPassword(userRegistrationDTO.getPassword()).
            setUsername(userRegistrationDTO.getUsername());
        userRepository.save(user);

        return true;
    }

    public boolean login(UserLoginDTO userLoginDTO) {
        Optional<UserEntity> user = userRepository.findByUsernameAndPassword(userLoginDTO.getUsername(), userLoginDTO.getPassword());
        if (!user.isPresent()) {
            return false;
        }
        this.loggedUser.login(user.get());

        return true;

    }

    public void logout() {
        this.loggedUser.logout();
    }

    public boolean isLoggedIn() {
        return this.loggedUser.getId() > 0;
    }

    public long getLoggedUserId() {
        return this.loggedUser.getId();
    }
}
