package bg.softuni.FindYourHome.repository;

import bg.softuni.FindYourHome.model.entity.TypeHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeHouseRepository extends JpaRepository<TypeHouse, Long> {
}
