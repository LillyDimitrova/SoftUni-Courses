package bg.softuni.intro.cats.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "cats")
public class CatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long catId;

    @Column(nullable = false)
    private String catName;

    @ManyToOne
    private OwnerEntity owner;


    public Long getCatId() {
        return catId;
    }

    public CatEntity setCatId(Long catId) {
        this.catId = catId;
        return this;
    }

    public String getCatName() {
        return catName;
    }

    public CatEntity setCatName(String catName) {
        this.catName = catName;
        return this;
    }

    public OwnerEntity getOwner() {
        return owner;
    }

    public CatEntity setOwner(OwnerEntity owner) {
        this.owner = owner;
        return this;
    }
}
