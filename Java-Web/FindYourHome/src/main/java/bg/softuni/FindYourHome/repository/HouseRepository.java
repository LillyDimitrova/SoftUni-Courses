package bg.softuni.FindYourHome.repository;

import bg.softuni.FindYourHome.model.entity.HouseEntity;
import bg.softuni.FindYourHome.model.entity.enums.TypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HouseRepository extends JpaRepository<HouseEntity, Long> {

    //Optional<HouseEntity> findByType(TypeEnum type);
}
