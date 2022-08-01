package bg.softuni.FindYourHome.service;

import bg.softuni.FindYourHome.model.dtos.UserDetailDTO;
import bg.softuni.FindYourHome.model.dtos.UserRegistrationDTO;
import bg.softuni.FindYourHome.model.entity.RoleEntity;
import bg.softuni.FindYourHome.model.entity.UserEntity;
import bg.softuni.FindYourHome.model.enums.RoleEnum;
import bg.softuni.FindYourHome.model.error.ObjectNotFoundException;
import bg.softuni.FindYourHome.model.mapper.UserMapper;
import bg.softuni.FindYourHome.repository.RoleEntityRepository;
import bg.softuni.FindYourHome.repository.UserRepository;
import org.springframework.cache.annotation.Cacheable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final RoleEntityRepository roleEntityRepository;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private EmailService emailService;




    public UserService(UserRepository userRepository,
                       RoleEntityRepository roleEntityRepository,
                       UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, UserMapper userMapper, EmailService emailService) {
        this.userRepository = userRepository;
        this.roleEntityRepository = roleEntityRepository;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;

        this.userMapper = userMapper;
        this.emailService = emailService;
    }
    public void init() {
        if (userRepository.count() == 0 && roleEntityRepository.count() == 0) {
            RoleEntity adminRole = new RoleEntity().setRole(RoleEnum.ADMIN);
            RoleEntity userRole = new RoleEntity().setRole(RoleEnum.USER);
            userRole = roleEntityRepository.save(userRole);
            adminRole = roleEntityRepository.save(adminRole);
            initAdmin(List.of(adminRole, userRole));
        }
    }
    private void initAdmin(List<RoleEntity> roles) {
        UserEntity admin = new UserEntity().
                setRoles(roles).
                setUsername("admin").
                setFirstName("Admin").
                setLastName("Adminov").
                setEmail("admin@example.com").
                setPassword(passwordEncoder.encode("topsecret"));

        userRepository.save(admin);

    }

    public void registerAndLogin(UserRegistrationDTO userRegistrationDTO) {

        UserEntity newUser = userMapper.userDtoToUserEntity(userRegistrationDTO);
        newUser.setRoles(List.of(Objects.requireNonNull(Objects.requireNonNull(roleEntityRepository.findByRole(RoleEnum.USER).orElse(null))))).
        setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));

        this.userRepository.save(newUser);
        emailService.sendRegistrationEmail(newUser.getEmail(),
                newUser.getFirstName() + " " + newUser.getLastName());
        login(newUser);

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

    @Cacheable("users")
    public UserEntity getCurrentUser(UserDetails userDetails) {
        LOGGER.info("Getting student by name {}.", userDetails.getUsername());
       return userRepository.findByEmail(userDetails.getUsername()).
                orElseThrow();
    }

    @Cacheable("users")
    public List<UserDetailDTO> getAllUsers() {
        LOGGER.info("Getting all users.");

        return userRepository.findAll().stream().map(UserDetailDTO::new).collect(Collectors.toList());
    }

    public void removeUser(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
        userRepository.delete(user);
    }
}


