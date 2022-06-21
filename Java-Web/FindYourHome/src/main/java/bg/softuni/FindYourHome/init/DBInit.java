package bg.softuni.FindYourHome.init;

import bg.softuni.FindYourHome.model.entity.RoleEntity;
import bg.softuni.FindYourHome.model.enums.RoleEnum;
import bg.softuni.FindYourHome.repository.RoleEntityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {
    private final RoleEntityRepository roleEntityRepository;

    public DBInit(RoleEntityRepository roleEntityRepository) {
        this.roleEntityRepository = roleEntityRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (roleEntityRepository.count() == 0) {
            for (RoleEnum r : RoleEnum.values()) {
                RoleEntity role = new RoleEntity().setRole(r);
                roleEntityRepository.save(role);
            }
        }
    }
}
