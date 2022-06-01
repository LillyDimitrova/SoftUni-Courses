package bg.softuni.pathfinder.model;

import bg.softuni.pathfinder.model.enums.Level;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String gpxCoordinates;

    @Enumerated(EnumType.STRING)
    private Level level;
    
    private String name;

    @ManyToOne
    private User author;

    private String videoUrl;

    @OneToOne
    private Picture header;
    @OneToMany(targetEntity = Comment.class, mappedBy = "route", cascade = CascadeType.ALL)
    private Set<Comment> comments;

    @ManyToMany
    private Set<Category> categories;

    public Picture getHeader() {
        return header;
    }

    public Route setHeader(Picture header) {
        this.header = header;
        return this;
    }

    public Route() {
        this.comments = new HashSet<>();
        this.categories = new HashSet<>();
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public Route setCategories(Set<Category> categories) {
        this.categories = categories;
        return this;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public Route setComments(Set<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Route setId(Long id) {
        this.id = id;
        return this;
    }

    public String getGpxCoordinates() {
        return gpxCoordinates;
    }

    public Route setGpxCoordinates(String gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
        return this;
    }

    public Level getLevel() {
        return level;
    }

    public Route setLevel(Level level) {
        this.level = level;
        return this;
    }

    public String getName() {
        return name;
    }

    public Route setName(String name) {
        this.name = name;
        return this;
    }

    public User getAuthor() {
        return author;
    }

    public Route setAuthor(User author) {
        this.author = author;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public Route setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }
}
