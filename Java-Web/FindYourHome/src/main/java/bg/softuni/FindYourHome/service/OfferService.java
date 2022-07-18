package bg.softuni.FindYourHome.service;

import bg.softuni.FindYourHome.model.dtos.CreateOfferDTO;
import bg.softuni.FindYourHome.model.dtos.OfferDetailDTO;
import bg.softuni.FindYourHome.model.entity.*;
import bg.softuni.FindYourHome.model.mapper.OfferMapper;
import bg.softuni.FindYourHome.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

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
    OfferEntity offer = new OfferEntity();
    public boolean create( CreateOfferDTO createOfferDTO, UserDetails userDetails) {
         offer = offerMapper.addOfferDtoToOfferEntity(createOfferDTO);

        UserEntity seller = userRepository.findByEmail(userDetails.getUsername()).
        orElseThrow();
        offer.setSeller(seller);

        offerRepository.save(offer);

        return true;
    }

    public Page<OfferDetailDTO> getAllOffers(Pageable pageable) {

    return offerRepository.findAll(pageable).map(offerMapper::offerEntityToOfferDetailDTO);
    }
    public OfferDetailDTO getCurrentNewOffer(){
       return offerRepository.findById(offer.getId()).map(offerMapper::offerEntityToOfferDetailDTO).orElse(null);

    }

}
