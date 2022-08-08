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
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerIT {

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
    void testMyOfferShown() throws Exception {
        mockMvc.perform(get("/my-offers")).
                andExpect(status().isOk()).
                andExpect(view().name("my-offers"));
    }
    @WithMockUser(
            username = "user@example.com",
            roles = "USER"
    )
    @Test
    void testProfileShown() throws Exception {
        mockMvc.perform(get("/profile")).
                andExpect(status().isOk()).
                andExpect(view().name("profile"));
    }


    @Test
    void testHomePageShown() throws Exception {
        mockMvc.perform(get("/")).
                andExpect(status().isOk()).
                andExpect(view().name("index"));
    }
}
