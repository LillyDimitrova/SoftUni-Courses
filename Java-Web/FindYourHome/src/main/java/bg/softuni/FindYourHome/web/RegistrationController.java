package bg.softuni.FindYourHome.web;

import bg.softuni.FindYourHome.model.dtos.UserRegistrationDTO;
import bg.softuni.FindYourHome.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class RegistrationController {


    private final UserService userService;

    public RegistrationController(UserService userService) {
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


}
