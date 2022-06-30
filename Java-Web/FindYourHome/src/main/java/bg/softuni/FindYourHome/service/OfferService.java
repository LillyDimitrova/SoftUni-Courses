package bg.softuni.FindYourHome.service;

import bg.softuni.FindYourHome.model.dtos.CreateOfferDTO;
import bg.softuni.FindYourHome.model.entity.*;
import bg.softuni.FindYourHome.repository.*;
import bg.softuni.FindYourHome.session.CurrentUser;
import org.springframework.stereotype.Service;

@Service
public class OfferService {
    private final OfferRepository offerRepository;
    private final TypeHouseRepository typeHouseRepository;
    private final CategoryRepository categoryRepository;
    private final CurrentUser currentUser;
    private final UserRepository userRepository;
    private final HouseRepository houseRepository;


    public OfferService(OfferRepository offerRepository, TypeHouseRepository typeHouseRepository, CategoryRepository categoryRepository, CurrentUser currentUser, UserRepository userRepository, HouseRepository houseRepository) {
        this.offerRepository = offerRepository;
        this.typeHouseRepository = typeHouseRepository;
        this.categoryRepository = categoryRepository;
        this.currentUser = currentUser;
        this.userRepository = userRepository;
        this.houseRepository = houseRepository;
    }

    public boolean create(CreateOfferDTO createOfferDTO) {
        TypeHouse typeHouse = typeHouseRepository.findByType(createOfferDTO.getType()).orElse(null);
        CategoryEntity category = categoryRepository.findByCategory(createOfferDTO.getCategory()).orElse(null);
        UserEntity seller = userRepository.getById(currentUser.getId());
        HouseEntity house = new HouseEntity()
                .setCategory(category)
                .setType(typeHouse)
                .setPrice(createOfferDTO.getPrice())
                .setDescription(createOfferDTO.getDescription())
                .setYearOfConstruction(createOfferDTO.getYearOfConstruction());
        houseRepository.save(house);
        if (typeHouse == null) {
            return false;
        }
        if (category == null) {
            return false;
        }
        OfferEntity offer = new OfferEntity().
                setDateTime(createOfferDTO.getDateTime()).
                setSeller(seller).
                setHouse(house);
        offerRepository.save(offer);


        return true;

    }
}
