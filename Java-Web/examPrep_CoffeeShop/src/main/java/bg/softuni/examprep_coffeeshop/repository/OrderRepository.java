package bg.softuni.examprep_coffeeshop.repository;

import bg.softuni.examprep_coffeeshop.model.entity.Category;
import bg.softuni.examprep_coffeeshop.model.entity.Order;
import bg.softuni.examprep_coffeeshop.model.entity.User;
import bg.softuni.examprep_coffeeshop.model.enums.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByName(CategoryNameEnum name);
}
