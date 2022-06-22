package bg.softuni.examprep_coffeeshop.service;

import bg.softuni.examprep_coffeeshop.model.dtos.OrderAddDTO;
import bg.softuni.examprep_coffeeshop.model.entity.Order;
import bg.softuni.examprep_coffeeshop.model.enums.CategoryNameEnum;
import bg.softuni.examprep_coffeeshop.repository.OrderRepository;
import bg.softuni.examprep_coffeeshop.session.CurrentUser;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CurrentUser currentUser;
    private final UserService userService;
    private final CategoryService categoryService;

    public OrderService(OrderRepository orderRepository, CurrentUser currentUser, UserService userService, CategoryService categoryService) {
        this.orderRepository = orderRepository;
        this.currentUser = currentUser;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    public void addOrder(OrderAddDTO orderAddDTO){
        Order order = new Order();
        order
                .setOrderTime(orderAddDTO.getOrderTime())
                .setCategory(categoryService.findByName(orderAddDTO.getCategory().getName()))
                .setDescription(orderAddDTO.getDescription())
                .setEmployee(userService.userRepository.getById(currentUser.getId()))
                .setName(orderAddDTO.getName())
                .setPrice(orderAddDTO.getPrice());
        orderRepository.save(order);
    }
}
