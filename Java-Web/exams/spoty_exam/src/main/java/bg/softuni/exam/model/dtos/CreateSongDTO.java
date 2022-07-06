package bg.softuni.exam.model.dtos;

import bg.softuni.exam.model.enums.StyleNameEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class CreateSongDTO {

    @Size(min = 3, max = 20 , message = "Performer name length must be between 3 and 20 characters")
    @NotBlank
    private String performer;

    @Size(min = 2, max = 20, message = "Title length must be between 2 and 20 characters ")
    @NotBlank
    private String title;

    @PastOrPresent(message = "The Date that cannot be in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    @Positive(message = "The duration must be a positive number")
    private Integer durationInSeconds;

    @NotNull
    private StyleNameEnum style;

    public CreateSongDTO() {
    }

    public String getPerformer() {
        return performer;
    }

    public CreateSongDTO setPerformer(String performer) {
        this.performer = performer;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CreateSongDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public CreateSongDTO setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public Integer getDurationInSeconds() {
        return durationInSeconds;
    }

    public CreateSongDTO setDurationInSeconds(Integer durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
        return this;
    }

    public StyleNameEnum getStyle() {
        return style;
    }

    public CreateSongDTO setStyle(StyleNameEnum style) {
        this.style = style;
        return this;
    }


}
