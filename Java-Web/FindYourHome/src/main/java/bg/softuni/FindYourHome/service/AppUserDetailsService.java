package bg.softuni.FindYourHome.service;

import bg.softuni.FindYourHome.model.entity.RoleEntity;
import bg.softuni.FindYourHome.model.entity.UserEntity;
import bg.softuni.FindYourHome.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.stream.Collectors;

public class AppUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).map(this::map).orElseThrow(() -> new UsernameNotFoundException("User with email " + username + " not found!"));
    }
    private UserDetails map(UserEntity userEntity) {

                return User.builder().
                username(userEntity.getUsername()).
                password(userEntity.getPassword()).
                authorities(userEntity.
                        getRoles().
                        stream().
                        map(this::map).
                        collect(Collectors.toList())).
                build();


    }
    private GrantedAuthority map(RoleEntity roleEntity) {
        return new SimpleGrantedAuthority("ROLE_" + roleEntity.getRole().name());
    }
}
