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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

        UserDetails userDetails = userDetailsService.loadUserByUsername(userEntity.getUsername());
        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private void login(UserEntity userEntity) {
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(userEntity.getEmail());

        Authentication auth =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );
    }
}
