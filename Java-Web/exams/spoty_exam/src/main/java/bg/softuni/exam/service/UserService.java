package bg.softuni.exam.service;

import bg.softuni.exam.model.dtos.SongDTO;
import bg.softuni.exam.model.dtos.UserLoginDTO;
import bg.softuni.exam.model.dtos.UserRegistrationDTO;
import bg.softuni.exam.model.entity.BaseEntity;
import bg.softuni.exam.model.entity.SongEntity;
import bg.softuni.exam.model.entity.UserEntity;
import bg.softuni.exam.repository.SongRepository;
import bg.softuni.exam.repository.UserRepository;
import bg.softuni.exam.session.CurrentUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    private final SongRepository songRepository;

    public UserService(UserRepository userRepository, CurrentUser currentUser, SongRepository songRepository) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.songRepository = songRepository;
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

    public void saveSong(SongEntity song) {
        currentUser.add(song);
    }

    public List<SongDTO> getAllCurrentSongs() {
       return currentUser.getCurrentPlayList().stream().map(SongDTO::new).collect(Collectors.toList());
    }

    public void addCurrentSong(Long id) {
        currentUser.add(songRepository.findById(id).orElse(null));;
    }

    public Integer getTotalSum(){
       return getAllCurrentSongs().stream().mapToInt(SongDTO::getDuration).sum();
    }

    public void removeAllSongs() {
        currentUser.removeAll();
    }

}
