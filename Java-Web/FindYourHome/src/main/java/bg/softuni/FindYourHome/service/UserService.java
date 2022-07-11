package bg.softuni.FindYourHome.service;

import bg.softuni.FindYourHome.model.dtos.UserRegistrationDTO;
import bg.softuni.FindYourHome.model.entity.RoleEntity;
import bg.softuni.FindYourHome.model.entity.UserEntity;
import bg.softuni.FindYourHome.model.enums.RoleEnum;
import bg.softuni.FindYourHome.repository.RoleEntityRepository;
import bg.softuni.FindYourHome.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleEntityRepository roleEntityRepository;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;




    public UserService(UserRepository userRepository,
                       RoleEntityRepository roleEntityRepository,
                       UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleEntityRepository = roleEntityRepository;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;

    }
    public void init() {
        if (userRepository.count() == 0 && roleEntityRepository.count() == 0) {
            RoleEntity adminRole = new RoleEntity().setRole(RoleEnum.ADMIN);
            RoleEntity moderatorRole = new RoleEntity().setRole(RoleEnum.MODERATOR);
            adminRole = roleEntityRepository.save(adminRole);
            moderatorRole = roleEntityRepository.save(moderatorRole);
            initAdmin(List.of(adminRole, moderatorRole));
            initModerator(List.of(moderatorRole));
        }
    }
    private void initAdmin(List<RoleEntity> roles) {
        UserEntity admin = new UserEntity().
                setRoles(roles).
                setFirstName("Admin").
                setLastName("Adminov").
                setEmail("admin@example.com").
                setPassword(passwordEncoder.encode("topsecret"));

        userRepository.save(admin);
    }
    private void initModerator(List<RoleEntity> roles) {
        UserEntity moderator = new UserEntity().
                setRoles(roles).
                setFirstName("Moderator").
                setLastName("Moderatorov").
                setEmail("moderator@example.com").
                setPassword(passwordEncoder.encode("topsecret"));

        userRepository.save(moderator);
    }
    public void registerAndLogin(UserRegistrationDTO userRegistrationDTO) {

        UserEntity newUser =
                new UserEntity().
                        setEmail(userRegistrationDTO.getEmail()).
                        setUsername(userRegistrationDTO.getUsername()).
                        setFirstName(userRegistrationDTO.getFirstName()).
                        setLastName(userRegistrationDTO.getLastName()).
                        setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));

        userRepository.save(newUser);

        UserDetails userDetails =
                userDetailsService.loadUserByUsername(newUser.getUsername());

        Authentication auth =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );

        SecurityContextHolder.
                getContext().
                setAuthentication(auth);
    }

    public UserEntity getCurrentUser() {
        String currentUserName;
        Object principal = SecurityContextHolder. getContext(). getAuthentication(). getPrincipal();
        if (principal instanceof UserDetails) {
            currentUserName = ((UserDetails)principal). getUsername();
        } else {
            currentUserName = principal. toString();
        }
        UserEntity currentUser = userRepository.findByUsername(currentUserName).orElse(null);
        return currentUser;
    }
}


