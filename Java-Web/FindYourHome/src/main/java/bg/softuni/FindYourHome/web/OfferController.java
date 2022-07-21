package bg.softuni.FindYourHome.web;

import bg.softuni.FindYourHome.model.dtos.CreateOfferDTO;
import bg.softuni.FindYourHome.model.dtos.OfferDetailDTO;
import bg.softuni.FindYourHome.service.OfferService;
import bg.softuni.FindYourHome.service.UserService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/all-offers")
    public String all(Model model, @PageableDefault(
            sort = "price",
            direction = Sort.Direction.ASC,
            page = 0,
            size = 3) Pageable pageable) {
        model.addAttribute("offers", offerService.getAllOffers(pageable));
        return "all-offers";
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
    public String addOffers(@Valid CreateOfferDTO createOfferDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes,  @AuthenticationPrincipal UserDetails userDetails, Model model) {


        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("createOfferDTO", createOfferDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createOfferDTO", bindingResult);

            return "redirect:/offer-add";
        } else {
             offerService.create(createOfferDTO, userDetails);
            OfferDetailDTO offerDetailDTO = offerService.getCurrentNewOffer();
            model.addAttribute("newOffer",offerDetailDTO);

            return "added-offer";
        }
    }
    @GetMapping("/all-offers/details/{id}")
    public String getOfferDetail(@PathVariable("id") Long id, Model model)  {

            model.addAttribute("offer", offerService.getOfferById(id));

        return "details";
    }
}
