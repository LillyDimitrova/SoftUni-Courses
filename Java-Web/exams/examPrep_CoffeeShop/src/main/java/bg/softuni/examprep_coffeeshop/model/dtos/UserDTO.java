package bg.softuni.examprep_coffeeshop.model.dtos;

import bg.softuni.examprep_coffeeshop.model.entity.User;

public class UserDTO {

    private String username;
    private Integer countOfOrders;


    public UserDTO(User user) {
        this.username = user.getUsername();
        this.countOfOrders = user.getOrders().size();
    }

    public String getUsername() {
        return username;
    }

    public Integer getCountOfOrders() {
        return countOfOrders;
    }
}
