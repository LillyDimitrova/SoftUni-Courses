package bg.softuni.FindYourHome.service;

import bg.softuni.FindYourHome.model.dtos.CreateOfferDTO;
import bg.softuni.FindYourHome.model.entity.*;
import bg.softuni.FindYourHome.model.mapper.OfferMapper;
import bg.softuni.FindYourHome.repository.*;
import bg.softuni.FindYourHome.session.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferService {
    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;
    private final TypeHouseRepository typeHouseRepository;
    private final CategoryRepository categoryRepository;
    private final CurrentUser currentUser;
    private final UserRepository userRepository;

@Autowired
    public OfferService(OfferRepository offerRepository, OfferMapper offerMapper, TypeHouseRepository typeHouseRepository, CategoryRepository categoryRepository, CurrentUser currentUser, UserRepository userRepository) {
        this.offerRepository = offerRepository;
    this.offerMapper = offerMapper;
    this.typeHouseRepository = typeHouseRepository;
        this.categoryRepository = categoryRepository;
        this.currentUser = currentUser;
        this.userRepository = userRepository;

    }

    public boolean create(CreateOfferDTO createOfferDTO) {
        UserEntity seller = userRepository.getById(currentUser.getId());
        TypeHouse typeHouse = typeHouseRepository.findByType(createOfferDTO.getType()).orElse(null);
        CategoryEntity category = categoryRepository.findByCategory(createOfferDTO.getCategory()).orElse(null);
        if (typeHouse == null) {
            return false;
        }
        if (category == null) {
            return false;
        }
        OfferEntity offer = offerMapper.addOfferDtoToOfferEntity(createOfferDTO);
//                setSeller(seller).
//                setDescription(createOfferDTO.getDescription()).
//                setTypeHouse(createOfferDTO.getType()).
//                setYearOfConstruction(createOfferDTO.getYearOfConstruction()).
//                setCategory(createOfferDTO.getCategory()).
//                setPrice(createOfferDTO.getPrice()).
//                setInstant(Instant.now()).
//                setImageUrl("https://images.pexels.com/photos/106399/pexels-photo-106399.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");

//        offerRepository.save(offer);

        offer.setSeller(seller);
        offerRepository.save(offer);

        return true;

    }
}
