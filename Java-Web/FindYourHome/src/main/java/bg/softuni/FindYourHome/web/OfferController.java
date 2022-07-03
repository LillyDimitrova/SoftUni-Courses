package bg.softuni.FindYourHome.web;

import bg.softuni.FindYourHome.model.dtos.CreateOfferDTO;
import bg.softuni.FindYourHome.service.OfferService;
import bg.softuni.FindYourHome.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class OfferController {
    private final OfferService offerService;
    private final UserService userService;

    public OfferController(OfferService offerService, UserService userService) {
        this.offerService = offerService;
        this.userService = userService;
    }
    @GetMapping("/offers/all")
    public String allOffers() {
        return "offers";
    }


    @ModelAttribute("createOfferDTO")
    public CreateOfferDTO initOffer(){
        return new CreateOfferDTO();
    }

    @GetMapping("/offer-add")
    public String addOffers(){
        return "/offer-add";
    }

    @PostMapping("offer-add")
    public String addOffers(@Valid CreateOfferDTO createOfferDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (!this.userService.isLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("createOfferDTO", createOfferDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createOfferDTO", bindingResult);

            return "redirect:/offer-add";
        }
        offerService.create(createOfferDTO);

        return "redirect:/offers/all";
    }

}
