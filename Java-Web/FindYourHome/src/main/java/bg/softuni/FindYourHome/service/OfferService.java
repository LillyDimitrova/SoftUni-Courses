package bg.softuni.FindYourHome.service;

import bg.softuni.FindYourHome.model.dtos.CreateOfferDTO;
import bg.softuni.FindYourHome.model.entity.*;
import bg.softuni.FindYourHome.repository.*;
import bg.softuni.FindYourHome.session.CurrentUser;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class OfferService {
    private final OfferRepository offerRepository;
    private final TypeHouseRepository typeHouseRepository;
    private final CategoryRepository categoryRepository;
    private final CurrentUser currentUser;
    private final UserRepository userRepository;


    public OfferService(OfferRepository offerRepository, TypeHouseRepository typeHouseRepository, CategoryRepository categoryRepository, CurrentUser currentUser, UserRepository userRepository) {
        this.offerRepository = offerRepository;
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
        OfferEntity offer = new OfferEntity().
                setSeller(seller).
                setDescription(createOfferDTO.getDescription()).
                setTypeHouse(createOfferDTO.getType()).
                setYearOfConstruction(createOfferDTO.getYearOfConstruction()).
                setCategory(createOfferDTO.getCategory()).
                setPrice(createOfferDTO.getPrice()).
                setInstant(Instant.now()).
                setImageUrl("https://images.pexels.com/photos/106399/pexels-photo-106399.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");

        offerRepository.save(offer);

        return true;

    }
}
