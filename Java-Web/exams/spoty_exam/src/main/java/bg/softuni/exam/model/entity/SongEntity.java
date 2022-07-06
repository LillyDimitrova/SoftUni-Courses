package bg.softuni.exam.model.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "songs")
public class SongEntity<S extends BaseEntity> extends BaseEntity{

    private String performer;
    private String title ;
    private Integer duration;
    private LocalDate releaseDate;
    @ManyToOne
    private StyleEntity style;

    @ManyToOne
    private UserEntity user;

    public UserEntity getUser() {
        return user;
    }

    public SongEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public SongEntity() {
    }

    public String getPerformer() {
        return performer;
    }

    public SongEntity setPerformer(String performer) {
        this.performer = performer;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SongEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public SongEntity setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public SongEntity setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public StyleEntity getStyle() {
        return style;
    }

    public SongEntity setStyle(StyleEntity style) {
        this.style = style;
        return this;
    }
}
