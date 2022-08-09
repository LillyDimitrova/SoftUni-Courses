package bg.softuni.FindYourHome.web;
import bg.softuni.FindYourHome.model.entity.UserEntity;
import bg.softuni.FindYourHome.util.TestDataUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

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
                        param("imageUrl", "testImage").

                        with(csrf())
                ).

                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/added-offer"));

    }

    @Test
    void testAllOfferShown() throws Exception {
        mockMvc.perform(get("/all-offers")).
                andExpect(status().isOk()).
                andExpect(view().name("all-offers"));
    }

    @WithUserDetails(value = "user@example.com",
            userDetailsServiceBeanName = "testUserDataService")

    @Test
    void testsearchQuery() throws Exception {
        mockMvc.perform(get("/all-offers/search")).
                andExpect(status().isOk()).
                andExpect(view().name("offer-search"));
    }
}
