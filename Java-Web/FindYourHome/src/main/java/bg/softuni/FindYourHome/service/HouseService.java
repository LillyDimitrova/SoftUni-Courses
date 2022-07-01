package bg.softuni.FindYourHome.service;

import bg.softuni.FindYourHome.model.dtos.CreateOfferDTO;
import bg.softuni.FindYourHome.model.entity.CategoryEntity;
import bg.softuni.FindYourHome.model.entity.HouseEntity;
import bg.softuni.FindYourHome.model.entity.TypeHouse;
import bg.softuni.FindYourHome.repository.CategoryRepository;
import bg.softuni.FindYourHome.repository.HouseRepository;
import bg.softuni.FindYourHome.repository.TypeHouseRepository;
import org.springframework.stereotype.Service;

@Service
public class HouseService {
    private final CategoryRepository categoryRepository;
    private final TypeHouseRepository typeHouseRepository;

    private final HouseRepository houseRepository;

    public HouseService(CategoryRepository categoryRepository, TypeHouseRepository typeHouseRepository, HouseRepository houseRepository) {
        this.categoryRepository = categoryRepository;
        this.typeHouseRepository = typeHouseRepository;
        this.houseRepository = houseRepository;
    }

//    public HouseEntity createHouse(CreateOfferDTO createOfferDTO) {
//        TypeHouse typeHouse = typeHouseRepository.findByType(createOfferDTO.getType()).orElse(null);
//        CategoryEntity category = categoryRepository.findByCategory(createOfferDTO.getCategory()).orElse(null);
//
//        HouseEntity house = new HouseEntity()
//                .setCategory(category)
//                .setType(typeHouse)
//                .setPrice(createOfferDTO.getPrice())
//                .setDescription(createOfferDTO.getDescription())
//                .setYearOfConstruction(createOfferDTO.getYearOfConstruction());
//        houseRepository.save(house);
//        return house;
//    }
}
