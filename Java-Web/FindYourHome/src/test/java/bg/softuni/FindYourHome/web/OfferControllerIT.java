package bg.softuni.FindYourHome.web;


import bg.softuni.FindYourHome.model.entity.CityEntity;
import bg.softuni.FindYourHome.model.entity.OfferEntity;
import bg.softuni.FindYourHome.model.entity.UserEntity;
import bg.softuni.FindYourHome.model.enums.CategoryEnum;
import bg.softuni.FindYourHome.model.enums.TypeHouseEnum;
import bg.softuni.FindYourHome.repository.OfferRepository;
import bg.softuni.FindYourHome.repository.UserRepository;
import bg.softuni.FindYourHome.util.TestDataUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.validation.constraints.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDate;

import static java.nio.file.Files.delete;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class OfferControllerIT {



    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataUtils testDataUtils;

    private UserEntity testUser;




    @BeforeEach
    void setUp() {
        testUser = testDataUtils.createTestUser("user@example.com");


    }
    @AfterEach
    void tearDown() {
        testDataUtils.cleanUpDatabase();
    }
    @WithMockUser(
            username = "user@example.com",
            roles = "USER"
    )
    @Test
    void testAddOfferShown() throws Exception {
        mockMvc.perform(get("/offer-add")).
                andExpect(status().isOk()).
                andExpect(view().name("offer-add"));
    }


    @WithUserDetails(value = "user@example.com",
            userDetailsServiceBeanName = "testUserDataService")
    @Test
    void testAddOffer() throws Exception {
        mockMvc.perform(post("/offer-add").
                        param("type", "APARTMENT").
                        param("category", "MULTIROOM").
                        param("city", "Plovdiv").
                        param("price", "85600").
                        param("yearOfConstruction", "2022-08-02").
                        param("description", "test").

                        with(csrf())
                ).

                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/added-offer"));

    }

}
