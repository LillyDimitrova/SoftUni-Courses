package bg.softuni.FindYourHome.service;

import bg.softuni.FindYourHome.model.dtos.UserRegistrationDTO;
import bg.softuni.FindYourHome.model.entity.UserEntity;
import bg.softuni.FindYourHome.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
                setEmail(userRegistrationDTO.getEmail());

        userRepository.save(user);

        return true;
    }
}
