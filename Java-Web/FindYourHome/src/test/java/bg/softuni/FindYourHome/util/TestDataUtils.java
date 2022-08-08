package bg.softuni.FindYourHome.util;

import bg.softuni.FindYourHome.model.entity.*;
import bg.softuni.FindYourHome.model.enums.CategoryEnum;
import bg.softuni.FindYourHome.model.enums.RoleEnum;
import bg.softuni.FindYourHome.model.enums.TypeHouseEnum;
import bg.softuni.FindYourHome.repository.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Collectors;

@Component
public class TestDataUtils {
    private final CategoryRepository categoryRepository;
    private final CitiesRepository citiesRepository;
    private final OfferRepository offerRepository;
    private final RoleEntityRepository roleEntityRepository;
    private final TypeHouseRepository typeHouseRepository;
    private final UserRepository userRepository;

    public TestDataUtils(CategoryRepository categoryRepository,
                         CitiesRepository citiesRepository,
                         OfferRepository offerRepository,
                         RoleEntityRepository roleEntityRepository,
                         TypeHouseRepository typeHouseRepository,
                         UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.citiesRepository = citiesRepository;
        this.offerRepository = offerRepository;
        this.roleEntityRepository = roleEntityRepository;
        this.typeHouseRepository = typeHouseRepository;
        this.userRepository = userRepository;
    }

    private void initRoles() {
        if (roleEntityRepository.count() == 0) {
            RoleEntity adminRole = new RoleEntity().setRole(RoleEnum.ADMIN);
            RoleEntity userRole = new RoleEntity().setRole(RoleEnum.USER);

            roleEntityRepository.save(adminRole);
            roleEntityRepository.save(userRole);
        }
    }
    public UserEntity createTestAdmin(String email) {

        initRoles();

        var admin = new UserEntity().
                setEmail(email).
                setUsername("usernameAdmin").
                setFirstName("Admin").
                setLastName("Adminov").
                setPassword("topsecret").
                setRoles(roleEntityRepository.findAll());

        return userRepository.save(admin);
    }
    public UserEntity createTestUser(String email) {

        initRoles();

        var user = new UserEntity().
                setEmail(email).
                setUsername("usernameUser").
                setFirstName("User").
                setLastName("Userov").
                setPassword("topsecret").
                setRoles(roleEntityRepository.
                        findAll().stream().
                        filter(r -> r.getRole() != RoleEnum.ADMIN).collect(Collectors.toList()));

        return userRepository.save(user);
    }

    public OfferEntity createTestOffer(UserEntity seller) {
        var offerEntity = new OfferEntity().
                setPrice(BigDecimal.TEN).
                setDescription("Test description").
                setCity(createTestCity()).
                setCategory(CategoryEnum.ONE_ROOM).
                setTypeHouse(TypeHouseEnum.APARTMENT).
                setYearOfConstruction(LocalDate.ofEpochDay(01/01/1995)).
                setImageUrl("https://www.arhitekti.bg/files/projectsgallery/project_26/Proekt_za_kyshta_Harizma1.jpg").
                setSeller(seller);

        return offerRepository.save(offerEntity);
    }

    public CityEntity createTestCity() {
        var cityEntity = new CityEntity().setName("Plovdiv");
        return citiesRepository.save(cityEntity);
    }

    public void cleanUpDatabase() {
        offerRepository.deleteAll();
        userRepository.deleteAll();
        roleEntityRepository.deleteAll();
        citiesRepository.deleteAll();
    }

}
