package bg.softuni.exam.web;

import bg.softuni.exam.model.dtos.CreateSongDTO;
import bg.softuni.exam.service.SongService;
import bg.softuni.exam.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class SongController {

    private SongService songService;
    private final UserService userService;

    public SongController(SongService songService, UserService userService) {
        this.songService = songService;
        this.userService = userService;
    }

    @ModelAttribute("createSongDTO")
    public CreateSongDTO initOrder(){
        return new CreateSongDTO();
    }

    @GetMapping("/song-add")
    public String songs(){
        return "/song-add";
    }

    @PostMapping("/song-add")
    public String songs(@Valid CreateSongDTO createSongDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

//        if (!this.userService.isLoggedIn()) {
//            return "redirect:/";
//        }

        if (bindingResult.hasErrors() || !songService.create(createSongDTO)){
            redirectAttributes.addFlashAttribute("createSongDTO", createSongDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createSongDTO", bindingResult);

            return "redirect:/song-add";
        }

        return "redirect:/home";
    }
    @GetMapping("/songs/add/{id}")
    public String addCurrentSong(@PathVariable Long id){
        userService.addCurrentSong(id);

        return "redirect:/home";
    }
    @GetMapping("/songs/remove")
    public String removeAll(){
        userService.removeAllSongs();

        return "redirect:/home";
    }

}
