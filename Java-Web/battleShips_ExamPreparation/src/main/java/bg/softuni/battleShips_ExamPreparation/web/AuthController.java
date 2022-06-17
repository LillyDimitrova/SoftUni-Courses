package bg.softuni.battleShips_ExamPreparation.web;

import bg.softuni.battleShips_ExamPreparation.model.dto.UserRegistrationDTO;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AuthController {

    @ModelAttribute("userRegistrationDTO")
    public UserRegistrationDTO initRegistrationDto() {
        return new UserRegistrationDTO();
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegistrationDTO userRegistrationDTO,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegistrationDTO", userRegistrationDTO).
                               addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDTO", bindingResult);

            return "redirect:/register";
        }
        return "login";
    }
}
