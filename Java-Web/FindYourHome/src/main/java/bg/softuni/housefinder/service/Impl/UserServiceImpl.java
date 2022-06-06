package bg.softuni.housefinder.service.Impl;

import bg.softuni.housefinder.model.entity.UserEntity;
import bg.softuni.housefinder.model.entity.UserRoleEntity;
import bg.softuni.housefinder.model.enums.UserRoleEnum;
import bg.softuni.housefinder.model.service.UserLoginServiceModel;
import bg.softuni.housefinder.repository.UserRepository;
import bg.softuni.housefinder.repository.UserRoleRepository;
import bg.softuni.housefinder.service.UserService;
import bg.softuni.housefinder.user.CurrentUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    private final UserRoleRepository userRoleRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, CurrentUser currentUser, UserRoleRepository userRoleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void initializeUsersAndRoles() {
        initializeRoles();
        initializeUsers();

    }
    private void initializeUsers() {
        UserRoleEntity adminRole = userRoleRepository.findByRole(UserRoleEnum.ADMIN);
        UserRoleEntity userRole = userRoleRepository.findByRole(UserRoleEnum.USER);

        if (userRepository.count() == 0) {
            UserEntity admin = new UserEntity();
            admin
                    .setUsername("admin")
                    .setPassword(passwordEncoder.encode("test"))
                    .setFirstName("Admin")
                    .setLastName("Adminow")

                    .setActive(true);

            admin.setRoles(Set.of(adminRole, userRole));
            userRepository.save(admin);

            UserEntity pesho = new UserEntity();
            pesho
                    .setUsername("pesho")
                    .setPassword(passwordEncoder.encode("test"))
                    .setFirstName("Pesho")
                    .setLastName("Petrov")
                    .setActive(true);

            pesho.setRoles(Set.of(userRole));
            userRepository.save(pesho);
        }
    }

    private void initializeRoles() {
        if (userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity();
            adminRole.setRole(UserRoleEnum.ADMIN);

            UserRoleEntity userRole = new UserRoleEntity();
            userRole.setRole(UserRoleEnum.USER);

            userRoleRepository.saveAll(List.of(adminRole, userRole));
        }
    }

    @Override
    public boolean login(UserLoginServiceModel loginServiceModel) {
        Optional<UserEntity> userEntityOpt = userRepository.findByUsername(loginServiceModel.getUsername());

        if (userEntityOpt.isEmpty()) {
            logout();
            return false;
        } else {
            boolean success = passwordEncoder.matches(loginServiceModel.getRawPassword(), userEntityOpt.get().getPassword());
            if (success) {
                UserEntity loggedInUser = userEntityOpt.get();
                currentUser.
                        setLoggedIn(true).
                        setUserName(loggedInUser.getUsername()).
                        setFirstName(loggedInUser.getFirstName()).
                        setLastName(loggedInUser.getLastName());

                loggedInUser.getRoles().forEach(r -> currentUser.addRole(r.getRole()));
            }
            return success;
        }
    }

    @Override
    public void logout() {
        currentUser.clean();
    }
}
