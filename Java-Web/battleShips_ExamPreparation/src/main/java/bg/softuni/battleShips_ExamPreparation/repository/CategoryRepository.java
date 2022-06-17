package bg.softuni.battleShips_ExamPreparation.repository;

import bg.softuni.battleShips_ExamPreparation.model.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
