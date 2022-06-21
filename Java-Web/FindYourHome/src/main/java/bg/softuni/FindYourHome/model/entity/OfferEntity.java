package bg.softuni.FindYourHome.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "offers")
public class OfferEntity extends BaseEntity{

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToOne
    private HouseEntity house;

    @ManyToOne
    private UserEntity seller;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private String imageUrl;

    public OfferEntity() {
    }

    public String getDescription() {
        return description;
    }

    public OfferEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public HouseEntity getHouse() {
        return house;
    }

    public OfferEntity setHouse(HouseEntity house) {
        this.house = house;
        return this;
    }

    public UserEntity getSeller() {
        return seller;
    }

    public OfferEntity setSeller(UserEntity seller) {
        this.seller = seller;
        return this;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public OfferEntity setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
