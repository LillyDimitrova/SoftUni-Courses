package bg.softuni.battleShips_ExamPreparation.init;

import bg.softuni.battleShips_ExamPreparation.model.entity.CategoryEntity;
import bg.softuni.battleShips_ExamPreparation.model.enums.ShipType;
import bg.softuni.battleShips_ExamPreparation.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public DBInit(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (categoryRepository.count() == 0) {
            for (ShipType c : ShipType.values()) {
                CategoryEntity entity = new CategoryEntity().setName(c);
                categoryRepository.save(entity);
            }
        }
    }
}
