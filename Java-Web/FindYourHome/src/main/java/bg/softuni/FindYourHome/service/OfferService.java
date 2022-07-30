package bg.softuni.FindYourHome.service;

import bg.softuni.FindYourHome.model.dtos.CreateOfferDTO;
import bg.softuni.FindYourHome.model.dtos.OfferDetailDTO;
import bg.softuni.FindYourHome.model.dtos.SearchOfferDTO;
import bg.softuni.FindYourHome.model.entity.*;
import bg.softuni.FindYourHome.model.mapper.OfferMapper;
import bg.softuni.FindYourHome.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferService {
    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;
    private final TypeHouseRepository typeHouseRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    private final UserService userService;
    private final CitiesRepository citiesRepository;
    private final CityService cityService;


@Autowired
    public OfferService(OfferRepository offerRepository, OfferMapper offerMapper, TypeHouseRepository typeHouseRepository, CategoryRepository categoryRepository, UserRepository userRepository, UserService userService, CitiesRepository citiesRepository, CityService cityService) {
        this.offerRepository = offerRepository;
    this.offerMapper = offerMapper;
        this.typeHouseRepository = typeHouseRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;


    this.userService = userService;
    this.citiesRepository = citiesRepository;
    this.cityService = cityService;
}
    OfferEntity offer = new OfferEntity();
    public boolean create( CreateOfferDTO createOfferDTO, UserDetails userDetails) {
         offer = offerMapper.addOfferDtoToOfferEntity(createOfferDTO);

        CityEntity city = cityService.getCityByName(createOfferDTO.getCity());

        UserEntity seller = userRepository.findByEmail(userDetails.getUsername()).
        orElseThrow();
        offer.setCity(city);
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

    public OfferDetailDTO getOfferById(Long id) {
        return offerRepository.findById(id).map(offerMapper::offerEntityToOfferDetailDTO).orElse(null);
    }

    public List<OfferDetailDTO> getAllOfferByUserId(Long id) {
        return offerRepository.findAllBySellerId(id).stream().map(offerMapper::offerEntityToOfferDetailDTO).collect(Collectors.toList());
    }

    public void removeOffer(Long id) {
        OfferEntity offer1 = offerRepository.findById(id).orElse(null);
        offerRepository.delete(offer1);
    }

    public List<OfferDetailDTO> searchOffer(SearchOfferDTO searchOfferDTO) {
        return this.offerRepository.findAll(new OfferSpecification(searchOfferDTO)).
                stream().map(offerMapper::offerEntityToOfferDetailDTO).collect(Collectors.toList());
    }
}
