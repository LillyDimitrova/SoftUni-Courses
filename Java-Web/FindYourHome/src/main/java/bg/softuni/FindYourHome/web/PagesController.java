package bg.softuni.FindYourHome.web;

import bg.softuni.FindYourHome.service.OfferService;
import bg.softuni.FindYourHome.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller

public class PagesController {
    private final UserService userService;

    private final OfferService offerService;

    public PagesController(UserService userService, OfferService offerService) {
        this.userService = userService;
        this.offerService = offerService;
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
    @GetMapping("/details/delete/{id}")
    public String removeOffer(@PathVariable Long id) {
        offerService.removeOffer(id);
        return "redirect:/all-offers";
    }
}
