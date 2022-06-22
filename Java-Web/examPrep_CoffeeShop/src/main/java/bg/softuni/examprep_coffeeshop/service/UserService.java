package bg.softuni.examprep_coffeeshop.service;

import bg.softuni.examprep_coffeeshop.model.dtos.UserLoginDTO;
import bg.softuni.examprep_coffeeshop.model.dtos.UserRegistrationDTO;
import bg.softuni.examprep_coffeeshop.model.entity.User;
import bg.softuni.examprep_coffeeshop.repository.UserRepository;
import bg.softuni.examprep_coffeeshop.session.CurrentUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    public final UserRepository userRepository;
    public final CurrentUser currentUser;

    public UserService(UserRepository userRepository, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
    }

    public boolean register(UserRegistrationDTO registrationDTO) {
        if (!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())) {
            return false;
        }
        Optional<User> byEmail = userRepository.findByEmail(registrationDTO.getEmail());
        if (byEmail.isPresent()) {
            return false;
        }
        Optional<User> byUsername = userRepository.findByUsername(registrationDTO.getUsername());
        if (byUsername.isPresent()) {
            return false;
        }

        User user = new User();
        user.setUsername(registrationDTO.getUsername()).
                setEmail(registrationDTO.getEmail()).
                setFirstName(registrationDTO.getFirstName()).
                setLastName(registrationDTO.getLastName()).
                setPassword(registrationDTO.getPassword());
        userRepository.save(user);
        return true;
    }


    public boolean login(UserLoginDTO userLoginDTO) {

        Optional<User> user = userRepository.findByUsernameAndPassword(userLoginDTO.getUsername(), userLoginDTO.getPassword());
        if (!user.isPresent()) {
            return false;
        }
       this.currentUser.login(user.get());

        return true;
    }


}
