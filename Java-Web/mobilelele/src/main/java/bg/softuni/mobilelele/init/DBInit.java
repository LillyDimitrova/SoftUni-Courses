package bg.softuni.mobilelele.init;

import bg.softuni.mobilelele.model.entity.BrandEntity;
import bg.softuni.mobilelele.model.entity.ModelEntity;
import bg.softuni.mobilelele.model.entity.enums.CategoryEnum;
import bg.softuni.mobilelele.repository.BrandRepository;
import bg.softuni.mobilelele.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBInit implements CommandLineRunner {

    private final BrandRepository brandRepository;
    private final UserRepository userRepository;

    public DBInit(BrandRepository brandRepository, UserRepository userRepository) {
        this.brandRepository = brandRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {

        if(brandRepository.count() == 0) {
            BrandEntity ford = new BrandEntity().setName("Ford");

            ModelEntity fiesta = new ModelEntity();
             fiesta.setName("Fiesta")
                     .setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/7/7d/2017_Ford_Fiesta_Zetec_Turbo_1.0_Front.jpg/275px-2017_Ford_Fiesta_Zetec_Turbo_1.0_Front.jpg")
                     .setStartYear(1976)
                     .setCategory(CategoryEnum.CAR);

            ModelEntity escort = new ModelEntity();
            escort.setName("Escort")
                    .setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/f/f0/Ford-Escort-Mk2.JPG/275px-Ford-Escort-Mk2.JPG")
                    .setStartYear(1968)
                    .setEndYear(2002)
                    .setCategory(CategoryEnum.CAR);

            ford.setModels(List.of(fiesta, escort));
            fiesta.setBrand(ford);
            escort.setBrand(ford);

            brandRepository.save(ford);
        }
    }
}
