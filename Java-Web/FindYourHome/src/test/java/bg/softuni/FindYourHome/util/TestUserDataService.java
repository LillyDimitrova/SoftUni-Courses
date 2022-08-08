package bg.softuni.FindYourHome.util;


import bg.softuni.FindYourHome.model.user.CurrentUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class TestUserDataService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new CurrentUserDetails(
                "topsecret",
                username,
                "Test",
                "user",
                Collections.emptyList());
    }
}
