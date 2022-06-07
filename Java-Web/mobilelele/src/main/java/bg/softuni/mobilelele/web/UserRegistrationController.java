package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.binding.UserRegistrationBindingModel;
import bg.softuni.mobilelele.model.service.UserRegistrationServiceModel;
import bg.softuni.mobilelele.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserRegistrationController {

    private UserService userService;

    public UserRegistrationController(UserService userService, ModelMapper) {
        this.userService = userService;
    }

    @GetMapping("/users/register")
    public String registerUser() {
        return "auth-register";
    }

    @PostMapping("/users/register")
    public String register(UserRegistrationBindingModel userModel) {
        // TODO validation
        UserRegistrationServiceModel serviceModel
        return "redirect:/";
    }
}
