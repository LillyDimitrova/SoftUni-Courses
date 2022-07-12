package bg.softuni.FindYourHome.service;

import bg.softuni.FindYourHome.model.dtos.UserRegistrationDTO;
import bg.softuni.FindYourHome.model.entity.RoleEntity;
import bg.softuni.FindYourHome.model.entity.UserEntity;
import bg.softuni.FindYourHome.model.enums.RoleEnum;
import bg.softuni.FindYourHome.model.mapper.UserMapper;
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
import java.util.Objects;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleEntityRepository roleEntityRepository;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;




    public UserService(UserRepository userRepository,
                       RoleEntityRepository roleEntityRepository,
                       UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleEntityRepository = roleEntityRepository;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;

        this.userMapper = userMapper;
    }
    public void init() {
        if (userRepository.count() == 0 && roleEntityRepository.count() == 0) {
            RoleEntity adminRole = new RoleEntity().setRole(RoleEnum.ADMIN);
            RoleEntity moderatorRole = new RoleEntity().setRole(RoleEnum.MODERATOR);
            RoleEntity userRole = new RoleEntity().setRole(RoleEnum.USER);
            userRole = roleEntityRepository.save(userRole);
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

        UserEntity newUser = userMapper.userDtoToUserEntity(userRegistrationDTO);
        newUser.setRoles(List.of(Objects.requireNonNull(Objects.requireNonNull(roleEntityRepository.findByRole(RoleEnum.USER).orElse(null))))).
        setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));

        this.userRepository.save(newUser);
        login(newUser);
//        emailService.sendRegistrationEmail(newUser.getEmail(),
//                newUser.getFirstName() + " " + newUser.getLastName());
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

        SecurityContextHolder.
                getContext().
                setAuthentication(auth);
    }

    public UserEntity getCurrentUser(UserDetails userDetails) {
       return userRepository.findByEmail(userDetails.getUsername()).
                orElseThrow();
    }
}


