package bg.softuni.FindYourHome.service;

import bg.softuni.FindYourHome.model.dtos.UserRegistrationDTO;
import bg.softuni.FindYourHome.model.entity.RoleEntity;
import bg.softuni.FindYourHome.model.entity.UserEntity;
import bg.softuni.FindYourHome.model.enums.RoleEnum;
import bg.softuni.FindYourHome.repository.RoleEntityRepository;
import bg.softuni.FindYourHome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleEntityRepository roleEntityRepository;
    private final UserDetailsService appUserDetailsService;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository,
                       RoleEntityRepository roleEntityRepository,
                       UserDetailsService appUserDetailsService,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleEntityRepository = roleEntityRepository;
        this.appUserDetailsService = appUserDetailsService;
        this.passwordEncoder = passwordEncoder;

    }
    public void init() {
        if (userRepository.count() == 0 && roleEntityRepository.count() == 0) {
            RoleEntity adminRole = new RoleEntity().setRole(RoleEnum.ADMIN);
            adminRole = roleEntityRepository.save(adminRole);
            initAdmin(List.of(adminRole));
        }
    }
    private void initAdmin(List<RoleEntity> roles) {
        UserEntity admin = new UserEntity().
                setRoles(roles).
                setFirstName("Admin").
                setLastName("Adminov").
                setEmail("admin@admin.123").
                setPassword(passwordEncoder.encode("topsecret"));
        userRepository.save(admin);
    }
    public void registerAndLogin(UserRegistrationDTO userRegistrationDTO) {
        UserEntity userEntity = new UserEntity().
                setUsername(userRegistrationDTO.getUsername()).
                setEmail(userRegistrationDTO.getEmail()).
                setFirstName(userRegistrationDTO.getFirstName()).
                setLastName(userRegistrationDTO.getLastName()).
                setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        userRepository.save(userEntity);

        UserDetails userDetails = appUserDetailsService.loadUserByUsername(userEntity.getUsername());
        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(auth);
    }

//    public boolean register(UserRegistrationDTO userRegistrationDTO) {
//        if (!userRegistrationDTO.getPassword().equals(userRegistrationDTO.getConfirmPassword())) {
//            return false;
//        }
//
//        Optional<UserEntity> byEmail = userRepository.findByEmail(userRegistrationDTO.getEmail());
//        if (byEmail.isPresent()) {
//            return false;
//        }
//
//        Optional<UserEntity> byUsername = userRepository.findByUsername(userRegistrationDTO.getUsername());
//        if (byUsername.isPresent()) {
//            return false;
//        }
//
//        UserEntity user = new UserEntity();
//        user.setEmail(userRegistrationDTO.getEmail()).
//                setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword())).
//                setUsername(userRegistrationDTO.getUsername()).
//                setFirstName(userRegistrationDTO.getFirstName()).
//                setLastName(userRegistrationDTO.getLastName()).
//                setEmail(userRegistrationDTO.getEmail());
//        userRepository.save(user);
//
//        return true;
//    }
//
//    public boolean login(UserLoginDTO userLoginDTO) {
//        Optional<UserEntity> user = userRepository.findByUsername(userLoginDTO.getUsername());
//        if (!user.isPresent()) {
//            return false;
//        }
//
//        String rawPassword = userLoginDTO.getPassword();
//        String encodedPassword = user.get().getPassword();
//
//        this.currentUser.login(user.get());
//        boolean success = passwordEncoder.
//                matches(rawPassword, encodedPassword);
//        if (success) {
//            this.currentUser.login(user.get());
//
//        } else {
//            logout();
//        }
//        return success;
//    }
//    public void logout() {
//        this.currentUser.logout();
//    }
//
//    public boolean isLoggedIn() {
//        return this.currentUser.getId() > 0;
//    }
//
//    public long getLoggedUserId() {
//        return this.currentUser.getId();
//    }
}
