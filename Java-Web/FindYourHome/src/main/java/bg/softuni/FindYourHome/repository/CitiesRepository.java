package bg.softuni.FindYourHome.repository;

import bg.softuni.FindYourHome.model.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CitiesRepository extends JpaRepository<CityEntity, Long> {
    Optional<CityEntity> getByName(String city);
}
