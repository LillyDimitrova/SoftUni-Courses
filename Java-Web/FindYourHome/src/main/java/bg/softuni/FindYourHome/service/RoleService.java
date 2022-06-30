package bg.softuni.FindYourHome.service;

import bg.softuni.FindYourHome.model.entity.RoleEntity;
import bg.softuni.FindYourHome.model.enums.RoleEnum;
import bg.softuni.FindYourHome.repository.RoleEntityRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleEntityRepository roleEntityRepository;

    public RoleService(RoleEntityRepository roleEntityRepository) {
        this.roleEntityRepository = roleEntityRepository;
    }


    public void initRoles() {
        if (roleEntityRepository.count() == 0) {
            for (RoleEnum r : RoleEnum.values()) {
                RoleEntity role = new RoleEntity().setRole(r);
                roleEntityRepository.save(role);
            }
        }
    }
}
