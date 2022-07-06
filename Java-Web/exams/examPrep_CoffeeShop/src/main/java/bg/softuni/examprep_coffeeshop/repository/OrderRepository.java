package bg.softuni.examprep_coffeeshop.repository;
import bg.softuni.examprep_coffeeshop.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByName(String name);
    List<Order> findAllByOrderByPriceDesc();
    void deleteById(Long id);
}
