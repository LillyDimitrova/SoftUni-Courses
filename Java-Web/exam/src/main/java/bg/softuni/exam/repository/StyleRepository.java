package bg.softuni.exam.repository;

import bg.softuni.exam.model.entity.StyleEntity;
import bg.softuni.exam.model.enums.StyleNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StyleRepository extends JpaRepository<StyleEntity, Long> {
    Optional<StyleEntity> findByName(StyleNameEnum style);
}
