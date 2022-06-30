package bg.softuni.FindYourHome.service;

import bg.softuni.FindYourHome.model.entity.TypeHouse;
import bg.softuni.FindYourHome.model.enums.TypeHouseEnum;
import bg.softuni.FindYourHome.repository.TypeHouseRepository;
import org.springframework.stereotype.Service;

@Service
public class TypeHouseService {
    private final TypeHouseRepository typeHouseRepository;

    public TypeHouseService(TypeHouseRepository typeHouseRepository) {
        this.typeHouseRepository = typeHouseRepository;
    }

    public void initTypeHouse() {
        if(typeHouseRepository.count() == 0) {
            for (TypeHouseEnum type : TypeHouseEnum.values()) {
                TypeHouse typeHouse = new TypeHouse().setType(type);
                typeHouseRepository.save(typeHouse);
            }
        }
    }
}
