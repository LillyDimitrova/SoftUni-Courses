package bg.softuni.FindYourHome.repository;

import bg.softuni.FindYourHome.model.entity.OfferEntity;
import bg.softuni.FindYourHome.model.entity.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {

   // Optional<UserEntity> findByUsername(String username);
    //Optional<OfferEntity> findByCategory(CategoryEnum category);
}
