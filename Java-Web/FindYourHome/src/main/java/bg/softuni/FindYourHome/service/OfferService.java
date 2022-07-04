package bg.softuni.FindYourHome.service;

import bg.softuni.FindYourHome.model.dtos.CreateOfferDTO;
import bg.softuni.FindYourHome.model.entity.*;
import bg.softuni.FindYourHome.model.mapper.OfferMapper;
import bg.softuni.FindYourHome.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class OfferService {
    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;
    private final TypeHouseRepository typeHouseRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;



@Autowired
    public OfferService(OfferRepository offerRepository, OfferMapper offerMapper, TypeHouseRepository typeHouseRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.offerRepository = offerRepository;
        this.offerMapper = offerMapper;
        this.typeHouseRepository = typeHouseRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;

}

    public boolean create(CreateOfferDTO createOfferDTO) {
        String username;
        Object principal = SecurityContextHolder. getContext(). getAuthentication(). getPrincipal();
        if (principal instanceof UserDetails) {
             username = ((UserDetails)principal). getUsername();
        } else {
            username = principal. toString();
        }
        UserEntity seller = userRepository.findByUsername(username).orElse(null);
        TypeHouseEntity typeHouse = typeHouseRepository.findByType(createOfferDTO.getType()).orElse(null);
        CategoryEntity category = categoryRepository.findByCategory(createOfferDTO.getCategory()).orElse(null);
        if (typeHouse == null) {
            return false;
        }
        if (category == null) {
            return false;
        }
        OfferEntity offer = offerMapper.addOfferDtoToOfferEntity(createOfferDTO);

        offer.setSeller(seller);

        offerRepository.save(offer);

        return true;

    }
}
