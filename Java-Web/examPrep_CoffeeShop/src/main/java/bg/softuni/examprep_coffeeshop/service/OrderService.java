package bg.softuni.examprep_coffeeshop.service;

import bg.softuni.examprep_coffeeshop.model.dtos.OrderAddDTO;
import bg.softuni.examprep_coffeeshop.model.entity.Category;
import bg.softuni.examprep_coffeeshop.model.entity.Order;
import bg.softuni.examprep_coffeeshop.model.entity.User;
import bg.softuni.examprep_coffeeshop.repository.CategoryRepository;
import bg.softuni.examprep_coffeeshop.repository.OrderRepository;
import bg.softuni.examprep_coffeeshop.repository.UserRepository;
import bg.softuni.examprep_coffeeshop.session.CurrentUser;
import bg.softuni.examprep_coffeeshop.view.OrderViewModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
  private final CategoryRepository categoryRepository;
  private final UserRepository userRepository;
  private final CurrentUser currentUser;
  private final OrderRepository orderRepository;

    public OrderService(CategoryRepository categoryRepository, UserRepository userRepository, CurrentUser currentUser, OrderRepository orderRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.orderRepository = orderRepository;
    }


    public boolean createOrder(OrderAddDTO orderAddDTO) {

    Category category = this.categoryRepository.findByName(orderAddDTO.getCategory()).orElse(null);
    if (category == null) {
        return false;
    }
        Optional<User> owner = this.userRepository.findById(this.currentUser.getId());

        Order order = new Order().setName(orderAddDTO.getName())
                .setPrice(orderAddDTO.getPrice())
                .setDescription(orderAddDTO.getDescription())
                .setOrderTime(orderAddDTO.getOrderTime())
                .setCategory(category)
                .setEmployee(owner.get());

        orderRepository.save(order);

        return true;
    }

    public List<OrderViewModel> findAllOrderByPriceDesc() {
        Object orderToOrderViewModel = null;
       List<Order> list = orderRepository.findAllByOrderByPriceDesc();
       List<OrderViewModel> returnList = new ArrayList<>();
        for (Order e : list) {
            OrderViewModel orderViewModel = new OrderViewModel().setCategory(e.getCategory()).setPrice(e.getPrice()).setId(e.getId()).setName(e.getName());
            returnList.add(orderViewModel);
        }
        return returnList;
    }
}
