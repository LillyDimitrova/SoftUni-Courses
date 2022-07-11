package bg.softuni.FindYourHome.web;

import bg.softuni.FindYourHome.model.entity.UserEntity;
import bg.softuni.FindYourHome.model.user.CurrentUserDetails;
import bg.softuni.FindYourHome.model.views.UserProfileView;
import bg.softuni.FindYourHome.service.CurrentUserDetailService;
import bg.softuni.FindYourHome.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class PagesController {
    private final UserService userService;


    public PagesController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/pages/all")
    public String all() {
        return "all";
    }

    @GetMapping("/pages/moderators")
    public String moderators() {
        return "moderators";
    }

    @GetMapping("/pages/admins")
    public String admins() {
        return "admins";
    }

    @GetMapping("/pages/profile")
    public String profile(Principal principal, Model model) {
        UserEntity userEntity = userService.getCurrentUser();
        UserProfileView userProfileView = new UserProfileView().setEmail(userEntity.getEmail()).setFirstName(userEntity.getFirstName()).
                setLastName(userEntity.getLastName()).setUsername(userEntity.getUsername());

        model.addAttribute("user", userProfileView);
        return "profile";
    }
}
