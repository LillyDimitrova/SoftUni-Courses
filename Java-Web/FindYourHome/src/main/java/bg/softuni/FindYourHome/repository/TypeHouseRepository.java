package bg.softuni.FindYourHome.repository;

import bg.softuni.FindYourHome.model.entity.TypeHouse;
import bg.softuni.FindYourHome.model.enums.TypeHouseEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeHouseRepository extends JpaRepository<TypeHouse, Long> {
    Optional<TypeHouse> findByType(TypeHouseEnum type);
}
