package bg.softuni.exam.model.dtos;

import bg.softuni.exam.model.entity.BaseEntity;
import bg.softuni.exam.model.entity.SongEntity;

public class SongDTO {

    private Long id;
    private String performer;
    private String title ;
    private Integer duration;

    public SongDTO(SongEntity song) {
        this.id = song.getId();
        this.performer = song.getPerformer();
        this.title = song.getTitle();
        this.duration = song.getDuration();
    }

    public SongDTO setPerformer(String performer) {
        this.performer = performer;
        return this;
    }

    public SongDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public SongDTO setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public Long getId() {
        return id;
    }

    public SongDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPerformer() {
        return performer;
    }

    public String getTitle() {
        return title;
    }

    public Integer getDuration() {
        return duration;
    }

}
