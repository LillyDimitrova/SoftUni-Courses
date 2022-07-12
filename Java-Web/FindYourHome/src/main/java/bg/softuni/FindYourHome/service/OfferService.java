package bg.softuni.FindYourHome.service;

import bg.softuni.FindYourHome.model.dtos.CreateOfferDTO;
import bg.softuni.FindYourHome.model.entity.*;
import bg.softuni.FindYourHome.model.mapper.OfferMapper;
import bg.softuni.FindYourHome.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class OfferService {
    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;
    private final TypeHouseRepository typeHouseRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    private final UserService userService;


@Autowired
    public OfferService(OfferRepository offerRepository, OfferMapper offerMapper, TypeHouseRepository typeHouseRepository, CategoryRepository categoryRepository, UserRepository userRepository, UserService userService) {
        this.offerRepository = offerRepository;
        this.offerMapper = offerMapper;
        this.typeHouseRepository = typeHouseRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;

    this.userService = userService;
}

    public boolean create( CreateOfferDTO createOfferDTO, UserDetails userDetails) {
        OfferEntity offer = offerMapper.addOfferDtoToOfferEntity(createOfferDTO);

        UserEntity seller = userRepository.findByEmail(userDetails.getUsername()).
        orElseThrow();
//        TypeHouseEntity typeHouse = typeHouseRepository.findByType(createOfferDTO.getType()).orElse(null);
//        CategoryEntity category = categoryRepository.findByCategory(createOfferDTO.getCategory()).orElse(null);
//        if (typeHouse == null) {
//            return false;
//        }
//        if (category == null) {
//            return false;
//        }

        offer.setSeller(seller);

        offerRepository.save(offer);

        return true;

    }
}
