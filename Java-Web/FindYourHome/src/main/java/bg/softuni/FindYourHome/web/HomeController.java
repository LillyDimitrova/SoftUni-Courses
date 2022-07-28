package bg.softuni.FindYourHome.web;

import bg.softuni.FindYourHome.model.entity.UserEntity;
import bg.softuni.FindYourHome.model.views.UserProfileView;
import bg.softuni.FindYourHome.service.OfferService;
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

@Controller
public class HomeController{

    private final UserService userService;
    private final OfferService offerService;

    public HomeController(UserService userService, OfferService offerService) {
        this.userService = userService;
        this.offerService = offerService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        UserEntity userEntity = userService.getCurrentUser(userDetails);
        UserProfileView userProfileView = new UserProfileView().setEmail(userEntity.getEmail()).setFirstName(userEntity.getFirstName()).
                setLastName(userEntity.getLastName()).setUsername(userEntity.getUsername()).setCountOfOffers(userEntity.getOffers().size());
        model.addAttribute("user", userProfileView);
        return "profile";
    }

    @GetMapping("/my-offers")
    public String getMyOffers(Principal principal, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        UserEntity userEntity = userService.getCurrentUser(userDetails);
        model.addAttribute("myOffers",offerService.getAllOfferByUserId(userEntity.getId()));
        return "my-offers";
    }

}
