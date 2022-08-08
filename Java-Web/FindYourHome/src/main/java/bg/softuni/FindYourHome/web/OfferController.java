package bg.softuni.FindYourHome.web;

import bg.softuni.FindYourHome.model.dtos.CreateOfferDTO;
import bg.softuni.FindYourHome.model.dtos.OfferDetailDTO;
import bg.softuni.FindYourHome.model.dtos.SearchOfferDTO;
import bg.softuni.FindYourHome.service.OfferService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    public String addOffers(@Valid CreateOfferDTO createOfferDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes,  @AuthenticationPrincipal UserDetails userDetails) {


        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("createOfferDTO", createOfferDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createOfferDTO", bindingResult);

            return "redirect:/offer-add";
        }
             offerService.create(createOfferDTO, userDetails);


            return "redirect:/added-offer";

    }
    @GetMapping("added-offer")
    public String addedOffer(Model model) {
        OfferDetailDTO offerDetailDTO = offerService.getCurrentNewOffer();
        model.addAttribute("newOffer",offerDetailDTO);
        return "added-offer";
    }

    @GetMapping("/all-offers/search")
    public String searchQuery(@Valid SearchOfferDTO searchOfferDTO,
                              BindingResult bindingResult,
                              Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("searchOffer", searchOfferDTO);
            model.addAttribute(
                    "org.springframework.validation.BindingResult.searchOfferModel",
                    bindingResult);
            return "offer-search";
        }

        if (!model.containsAttribute("searchOffer")) {
            model.addAttribute("searchOffer", searchOfferDTO);
        }

        if (!searchOfferDTO.isEmpty()) {
            model.addAttribute("offers", offerService.searchOffer(searchOfferDTO));
        }

        return "offer-search";
    }

    @GetMapping("/all-offers/details/{id}")
    public String getOfferDetail(@PathVariable("id") Long id, Model model)  {

        model.addAttribute("offer", offerService.getOfferById(id));

        return "details";
    }

    @PreAuthorize("isOwner(#id)")
    @DeleteMapping("/all-offers/details/delete/{id}")
    public String delete(@PathVariable Long id) {

        offerService.removeOffer(id);
        return "redirect:/all-offers";
    }
}
