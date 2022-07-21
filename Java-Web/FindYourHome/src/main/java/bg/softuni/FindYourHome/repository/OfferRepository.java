package bg.softuni.FindYourHome.repository;

import bg.softuni.FindYourHome.model.dtos.OfferDetailDTO;
import bg.softuni.FindYourHome.model.entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {
    List<OfferEntity> findAllBySellerId(Long id);
}
