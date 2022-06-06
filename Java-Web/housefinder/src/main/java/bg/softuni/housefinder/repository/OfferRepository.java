package bg.softuni.housefinder.repository;

import bg.softuni.housefinder.model.entity.OfferEntity;
import bg.softuni.housefinder.model.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {

   // Optional<UserEntity> findByUsername(String username);
    Optional<OfferEntity> findByCategory(CategoryEnum category);
}
