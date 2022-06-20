package bg.softuni.battleShips_ExamPreparation.service;

import bg.softuni.battleShips_ExamPreparation.model.dto.CreateShipDTO;
import bg.softuni.battleShips_ExamPreparation.model.entity.CategoryEntity;
import bg.softuni.battleShips_ExamPreparation.model.entity.ShipEntity;
import bg.softuni.battleShips_ExamPreparation.model.entity.UserEntity;
import bg.softuni.battleShips_ExamPreparation.model.enums.ShipType;
import bg.softuni.battleShips_ExamPreparation.repository.CategoryRepository;
import bg.softuni.battleShips_ExamPreparation.repository.ShipRepository;
import bg.softuni.battleShips_ExamPreparation.repository.UserRepository;
import bg.softuni.battleShips_ExamPreparation.session.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShipService {

    private final ShipRepository shipRepository;
    private LoggedUser loggedUser;
    private UserRepository userRepository;
    private CategoryRepository categoryRepository;

    public ShipService(ShipRepository shipRepository, LoggedUser loggedUser, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.shipRepository = shipRepository;
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public boolean create(CreateShipDTO createShipDTO) {

        Optional<ShipEntity> byName = this.shipRepository.findByName(createShipDTO.getName());

        if (byName.isPresent()) {
            return false;
        }

        ShipType type = ShipType.BATTLE;
        switch(createShipDTO.getCategory()) {
            case 0:
                 type =  ShipType.BATTLE;
            case 1:
                type = ShipType.CARGO;
            case 2:
                type = ShipType.PATROL;
        };

        CategoryEntity category = this.categoryRepository.findByName(type);
        Optional<UserEntity> owner = this.userRepository.findById(this.loggedUser.getId());

        ShipEntity ship = new ShipEntity();
        ship.setName(createShipDTO.getName())
        .setPower(createShipDTO.getPower())
        .setHealth(createShipDTO.getHealth())
                .setCreated(createShipDTO.getCreated())
        .setCategory(category)
                .setUser(owner.get());
        shipRepository.save(ship);
        return true;
    }
}
