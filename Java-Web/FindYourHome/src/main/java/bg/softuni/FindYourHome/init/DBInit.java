package bg.softuni.FindYourHome.init;

import bg.softuni.FindYourHome.service.CategoryService;
import bg.softuni.FindYourHome.service.RoleService;
import bg.softuni.FindYourHome.service.TypeHouseService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {
    private final RoleService roleService;
    private final CategoryService categoryService;
    private final TypeHouseService typeHouseService;

    public DBInit(RoleService roleService, CategoryService categoryService, TypeHouseService typeHouseService) {
        this.roleService = roleService;
        this.categoryService = categoryService;
        this.typeHouseService = typeHouseService;
    }

    @Override
    public void run(String... args) throws Exception {
        roleService.initRoles();
        categoryService.initCategories();
        typeHouseService.initTypeHouse();
    }
}
