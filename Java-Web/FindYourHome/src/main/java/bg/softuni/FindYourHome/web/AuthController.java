package bg.softuni.FindYourHome.web;

import bg.softuni.FindYourHome.model.dtos.UserLoginDTO;
import bg.softuni.FindYourHome.model.dtos.UserRegistrationDTO;
import bg.softuni.FindYourHome.service.UserService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class AuthController {


    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register() {

        return "register";
    }

    @ModelAttribute("userRegistrationDTO")
    public UserRegistrationDTO initRegistrationDto() {
        return new UserRegistrationDTO();
    }

    @ModelAttribute("userLoginDTO")
    public UserLoginDTO userLoginDTO() {
        return new UserLoginDTO();
    }

    @PostMapping("/register")
    public String register(@Valid UserRegistrationDTO userRegistrationDTO,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes) {



        if (bindingResult.hasErrors() ) {
            redirectAttributes.addFlashAttribute("userRegistrationDTO", userRegistrationDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDTO", bindingResult);

            return "redirect:/register";
        }
        this.userService.registerAndLogin(userRegistrationDTO);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @PostMapping("/login")
    public String onFailedLogin(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String userName,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, userName);
        redirectAttributes.addFlashAttribute("badCredentials",
                true);

        return "redirect:/";
    }
    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
            httpSession.invalidate();
        return "redirect:/";
    }
}
