package bg.softuni.exam.service;

import bg.softuni.exam.model.dtos.CreateSongDTO;
import bg.softuni.exam.model.dtos.SongDTO;
import bg.softuni.exam.model.entity.BaseEntity;
import bg.softuni.exam.model.entity.SongEntity;
import bg.softuni.exam.model.entity.StyleEntity;
import bg.softuni.exam.model.enums.StyleNameEnum;
import bg.softuni.exam.repository.SongRepository;
import bg.softuni.exam.repository.StyleRepository;
import bg.softuni.exam.repository.UserRepository;
import bg.softuni.exam.session.CurrentUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongService {

    private CurrentUser currentUser;
    private UserRepository userRepository;
    private SongRepository songRepository;
    private StyleRepository styleRepository;

    public SongService(CurrentUser currentUser, UserRepository userRepository, SongRepository songRepository, StyleRepository styleRepository) {
        this.currentUser = currentUser;
        this.userRepository = userRepository;
        this.songRepository = songRepository;
        this.styleRepository = styleRepository;
    }

    public boolean create(CreateSongDTO createSongDTO) {
        StyleEntity style = this.styleRepository.findByName(createSongDTO.getStyle()).orElse(null);
        if (style == null) {
            return false;
        }

        SongEntity<BaseEntity> song = new SongEntity()
                .setDuration(createSongDTO.getDurationInSeconds())
                .setPerformer(createSongDTO.getPerformer())
                .setStyle(style)
                .setReleaseDate(createSongDTO.getReleaseDate())
                .setTitle(createSongDTO.getTitle());
        songRepository.save(song);

        return true;
    }


    public List<SongDTO> getAllPopSongs() {

        return songRepository.findAllByStyle(styleRepository.findByName(StyleNameEnum.POP).orElse(null)).stream().map(SongDTO::new).collect(Collectors.toList());
    }

    public List<SongDTO> getAllRockSongs() {
        return songRepository.findAllByStyle(styleRepository.findByName(StyleNameEnum.ROCK).orElse(null)).stream().map(SongDTO::new).collect(Collectors.toList());
    }

    public List<SongDTO> getAllJazzSongs() {
        return songRepository.findAllByStyle(styleRepository.findByName(StyleNameEnum.JAZZ).orElse(null)).stream().map(SongDTO::new).collect(Collectors.toList());
    }

    public List<SongDTO> getAllSongsCurrentUser() {
        return songRepository.findAllByUserId(currentUser.getId()).stream().map(SongDTO::new).collect(Collectors.toList());
    }

}
