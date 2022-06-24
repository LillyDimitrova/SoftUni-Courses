package bg.softuni.battleShips_ExamPreparation.repository;

import bg.softuni.battleShips_ExamPreparation.model.dto.ShipDTO;
import bg.softuni.battleShips_ExamPreparation.model.entity.ShipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipRepository extends JpaRepository<ShipEntity, Long> {

    Optional<ShipEntity> findByName(String name);

    Optional<ShipEntity> findByUserId(long loggedUserId);

    List<ShipEntity> findByUserIdNot(long loggedUserId);

    List<ShipEntity> findByOrderByHealthAscNameDescPowerAsc();
}
