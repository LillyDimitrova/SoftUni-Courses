package bg.softuni.FindYourHome.config;

import bg.softuni.FindYourHome.model.enums.RoleEnum;
import bg.softuni.FindYourHome.repository.UserRepository;
import bg.softuni.FindYourHome.service.AppUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.
                authorizeRequests().
                requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
                antMatchers("/", "/login", "/register").permitAll().
                antMatchers("/pages/admins").hasRole(RoleEnum.ADMIN.name()).
                anyRequest().
                authenticated().
        and().
                formLogin().
                loginPage("/login").
                usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
                passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
                defaultSuccessUrl("/").
                failureForwardUrl("/login-error").
        and().
                logout().
                logoutUrl("/logout").
                invalidateHttpSession(true).
                deleteCookies("JSESSIONID");

        return httpSecurity.build();
    }
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new AppUserDetailsService(userRepository);
    }
}
