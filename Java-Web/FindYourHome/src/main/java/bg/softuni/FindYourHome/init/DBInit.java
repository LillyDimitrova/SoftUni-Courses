package bg.softuni.FindYourHome.init;

import bg.softuni.FindYourHome.service.CategoryService;
import bg.softuni.FindYourHome.service.TypeHouseService;
import bg.softuni.FindYourHome.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {

    private final CategoryService categoryService;
    private final TypeHouseService typeHouseService;
    private final UserService userService;

    public DBInit(CategoryService categoryService, TypeHouseService typeHouseService, UserService userService) {

        this.categoryService = categoryService;
        this.typeHouseService = typeHouseService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.init();
        categoryService.initCategories();
        typeHouseService.initTypeHouse();
    }
}
