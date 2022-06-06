package bg.softuni.FindYourHome.service.Impl;

import bg.softuni.FindYourHome.model.entity.HouseEntity;
import bg.softuni.FindYourHome.model.enums.CategoryEnum;
import bg.softuni.FindYourHome.model.enums.TypeEnum;
import bg.softuni.FindYourHome.repository.HouseRepository;
import bg.softuni.FindYourHome.repository.UserRepository;
import bg.softuni.FindYourHome.repository.UserRoleRepository;
import bg.softuni.FindYourHome.service.HouseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseImpl implements HouseService {
    private HouseRepository houseRepository;
    private UserRoleRepository userRoleRepository;
    private UserRepository userRepository;

    public HouseImpl(HouseRepository houseRepository, UserRoleRepository userRoleRepository, UserRepository userRepository) {
        this.houseRepository = houseRepository;
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void initializeTypeHouse() {

//        if(TypeHouseRepository.count() == 0) {
//            TypeHouseEntity ford = new TypeHouseEntity().setType(TypeEnum.HOUSE);
//
//            TypeHouseEntity fiesta = new ModelEntity();
//            fiesta.setName("Fiesta")
//                    .setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/7/7d/2017_Ford_Fiesta_Zetec_Turbo_1.0_Front.jpg/275px-2017_Ford_Fiesta_Zetec_Turbo_1.0_Front.jpg")
//                    .setStartYear(1976)
//                    .setCategory(CategoryEnum.CAR);
//
//            ModelEntity escort = new ModelEntity();
//            escort.setName("Escort")
//                    .setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/f/f0/Ford-Escort-Mk2.JPG/275px-Ford-Escort-Mk2.JPG")
//                    .setStartYear(1968)
//                    .setEndYear(2002)
//                    .setCategory(CategoryEnum.CAR);
//
//            ford.setModels(List.of(fiesta, escort));
//            fiesta.setBrand(ford);
//            escort.setBrand(ford);
//
//            brandRepository.save(ford);

        if (houseRepository.count() == 0) {
            HouseEntity house = new HouseEntity();
            house
            .setType(TypeEnum.HOUSE)
                    .setImageUrl("https://images.pexels.com/photos/106399/pexels-photo-106399.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500")
                    .setYearOfConstruction(2015)
                    .setCategory(CategoryEnum.MULTIROOM);


            HouseEntity apartment = new HouseEntity();
            apartment
                    .setType(TypeEnum.APARTMENT)
                    .setImageUrl("https://en.realestates.bg/user_files/a/annie/7539008_115998933_big.jpg")
                    .setYearOfConstruction(2019)
                    .setCategory(CategoryEnum.THREE_BEDROOM);

            houseRepository.saveAll(List.of(house,apartment));
        }
    }
}
