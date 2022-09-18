package bg.myapp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_address")
public class UserAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Account user;

    @OneToMany(mappedBy = "id")
    private List<Address> address;

    public UserAddress() {
    }

    public Long getId() {
        return id;
    }

    public UserAddress setId(Long id) {
        this.id = id;
        return this;
    }

    public Account getUser() {
        return user;
    }

    public UserAddress setUser(Account user) {
        this.user = user;
        return this;
    }

    public List<Address> getAddress() {
        return address;
    }

    public UserAddress setAddress(List<Address> address) {
        this.address = address;
        return this;
    }
}
