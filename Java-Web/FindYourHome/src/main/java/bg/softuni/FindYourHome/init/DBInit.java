package bg.softuni.FindYourHome.init;

import bg.softuni.FindYourHome.repository.UserRepository;
import bg.softuni.FindYourHome.service.HouseService;
import bg.softuni.FindYourHome.service.Impl.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private UserServiceImpl userService;
    private HouseService houseService;

    public DBInit(UserRepository userRepository, PasswordEncoder passwordEncoder, UserServiceImpl userService, HouseService houseService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.houseService = houseService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.initializeUsersAndRoles();
        houseService.initializeTypeHouse();

    }
}
