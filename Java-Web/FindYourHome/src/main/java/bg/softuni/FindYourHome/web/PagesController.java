package bg.softuni.FindYourHome.web;

import bg.softuni.FindYourHome.model.dtos.UserDetailDTO;
import bg.softuni.FindYourHome.model.entity.UserEntity;
import bg.softuni.FindYourHome.model.views.UserProfileView;
import bg.softuni.FindYourHome.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;

@Controller

public class PagesController {
    private final UserService userService;


    public PagesController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/pages/moderators")
    public String moderators() {
        return "moderators";
    }

    @GetMapping("/pages/admins")
    public String admins() {
        return "admins";
    }

    @GetMapping("pages/admins/users")
    public String allUsers(Model model) {

       model.addAttribute("usersList", userService.getAllUsers());

        return "users";
    }
    @GetMapping("users/remove/{id}")
    public String removeUser(@PathVariable Long id) {
        userService.removeUser(id);
        return "redirect:/pages/admins/users";
    }
}
