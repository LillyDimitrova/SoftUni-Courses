package bg.softuni.battleShips_ExamPreparation.service;

import bg.softuni.battleShips_ExamPreparation.model.dto.UserRegistrationDTO;
import bg.softuni.battleShips_ExamPreparation.model.entity.UserEntity;
import bg.softuni.battleShips_ExamPreparation.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
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
            setFullName(userRegistrationDTO.getFullName()).
            setPassword(userRegistrationDTO.getPassword()).
            setUsername(userRegistrationDTO.getUsername());
        userRepository.save(user);

        return true;
    }
}
