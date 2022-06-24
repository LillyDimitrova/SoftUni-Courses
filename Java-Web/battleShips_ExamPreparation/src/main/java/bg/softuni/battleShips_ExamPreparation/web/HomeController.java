package bg.softuni.battleShips_ExamPreparation.web;

import bg.softuni.battleShips_ExamPreparation.model.dto.ShipDTO;
import bg.softuni.battleShips_ExamPreparation.model.dto.StartBattleDTO;
import bg.softuni.battleShips_ExamPreparation.service.AuthService;
import bg.softuni.battleShips_ExamPreparation.service.ShipService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {
    private final AuthService authService;
    private final ShipService shipService;

    public HomeController(AuthService authService, ShipService shipService) {
        this.authService = authService;
        this.shipService = shipService;
    }

    @ModelAttribute("startBattleDTO")
    public StartBattleDTO initBattleForm() {
        return new StartBattleDTO();
    }

    @GetMapping("/")
    public String loggedOutIndex() {
        if(this.authService.isLoggedIn()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String loggedInIndex(Model model) {
        if(!this.authService.isLoggedIn()) {
            return "redirect:/";
        }
        long loggedUserId = this.authService.getLoggedUserId();

        List<ShipDTO> ownShips = shipService.getShipsOwnedBy(loggedUserId);
        List<ShipDTO> enemyShips = shipService.getShipsNotOwnedBy(loggedUserId);
        List<ShipDTO> sortedShips = shipService.getAllSorted();

        model.addAttribute("ownShips", ownShips);
        model.addAttribute("enemyShips", enemyShips);
        model.addAttribute("sortedShips", sortedShips);

        return "home";
    }
}
