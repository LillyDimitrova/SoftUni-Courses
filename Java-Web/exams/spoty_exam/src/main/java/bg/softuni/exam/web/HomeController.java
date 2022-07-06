package bg.softuni.exam.web;

import bg.softuni.exam.model.dtos.SongDTO;
import bg.softuni.exam.model.entity.BaseEntity;
import bg.softuni.exam.model.entity.SongEntity;
import bg.softuni.exam.service.SongService;
import bg.softuni.exam.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private UserService userService;
    private SongService songService;

    public HomeController(UserService userService , SongService songService) {
        this.userService = userService;
        this.songService = songService;
    }

    @GetMapping("/")
    public String loggedOutIndex() {
        if(this.userService.isLoggedIn()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String loggedInIndex(Model model) {
        if(!this.userService.isLoggedIn()) {
            return "redirect:/";
        }
        long loggedUserId = this.userService.getLoggedUserId();

        List<SongDTO> popSongs = songService.getAllPopSongs();
        List<SongDTO> rockSongs = songService.getAllRockSongs();
        List<SongDTO> jazzSongs = songService.getAllJazzSongs();
        List<SongDTO> userSongs = userService.getAllCurrentSongs();

       model.addAttribute("popSongs", popSongs);
       model.addAttribute("rockSongs", rockSongs);
       model.addAttribute("jazzSongs", jazzSongs);
       model.addAttribute("userSongs", userSongs);
       model.addAttribute("totalSum", userService.getTotalSum());


        return "home";
    }
}
