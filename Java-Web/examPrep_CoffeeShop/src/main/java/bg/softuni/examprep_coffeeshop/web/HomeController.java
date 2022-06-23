package bg.softuni.examprep_coffeeshop.web;

import bg.softuni.examprep_coffeeshop.service.OrderService;
import bg.softuni.examprep_coffeeshop.service.UserService;
import bg.softuni.examprep_coffeeshop.session.CurrentUser;
import bg.softuni.examprep_coffeeshop.view.OrderViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final OrderService orderService;
    private final UserService userService;


    public HomeController(CurrentUser currentUser, OrderService orderService, UserService userService) {
        this.currentUser = currentUser;
        this.orderService = orderService;
        this.userService = userService;
    }


    @GetMapping("/home")
    public String loggedInIndex() {
        return "home";
    }

    @GetMapping
    public String index(Model model){
        if (currentUser.getId() == 0) {
            return "index";
        }

        List<OrderViewModel> orders = orderService.findAllOrderByPriceDesc();
        model.addAttribute("orders", orders);
        model.addAttribute("totalTime", orders.
                stream().
                map(orderViewModel -> orderViewModel.getCategory().getNeededTime()).
                reduce(Integer::sum).
                orElse(0));

        model.addAttribute("users",userService.findAllUserAndCountOrdersOrderByCountDes());
        return "home";
    }
}
