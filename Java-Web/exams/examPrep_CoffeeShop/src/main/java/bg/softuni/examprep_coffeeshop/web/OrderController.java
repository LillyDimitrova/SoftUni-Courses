package bg.softuni.examprep_coffeeshop.web;

import bg.softuni.examprep_coffeeshop.model.dtos.CreateOrderDTO;
import bg.softuni.examprep_coffeeshop.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @ModelAttribute("orderAddDTO")
    public CreateOrderDTO initOrder(){
        return new CreateOrderDTO();
    }

    @GetMapping("/orders/add")
    public String orders(){
        return "order-add";
    }

    @PostMapping("/orders/add")
    public String orders(@Valid CreateOrderDTO orderAddDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !orderService.createOrder(orderAddDTO)){
            redirectAttributes.addFlashAttribute("orderAddDTO", orderAddDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderAddDTO", bindingResult);

            return "redirect:/orders/add";
        }

        return "redirect:/home";
    }

    @GetMapping("/orders/ready/{id}")
    public String ready(@PathVariable Long id) {
        orderService.readyOrder(id);
        return "redirect:/home";
    }
}
