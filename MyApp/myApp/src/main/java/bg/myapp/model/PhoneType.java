package bg.myapp.model;

import javax.persistence.*;

@Entity
@Table(name = "phone_type")
public class PhoneType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String home_number;

    @Column
    private String mobile_number;

    @Column
    private String business_number;

    public PhoneType() {
    }

    public Long getId() {
        return id;
    }

    public PhoneType setId(Long id) {
        this.id = id;
        return this;
    }

    public String getHome_number() {
        return home_number;
    }

    public PhoneType setHome_number(String home_number) {
        this.home_number = home_number;
        return this;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public PhoneType setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
        return this;
    }

    public String getBusiness_number() {
        return business_number;
    }

    public PhoneType setBusiness_number(String business_number) {
        this.business_number = business_number;
        return this;
    }


}
