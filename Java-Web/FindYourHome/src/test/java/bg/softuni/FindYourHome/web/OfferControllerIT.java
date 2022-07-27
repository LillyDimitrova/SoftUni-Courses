package bg.softuni.FindYourHome.web;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class OfferControllerIT {


    @Autowired
    private MockMvc mockMvc;

    @Test
    void testOfferPageShown() throws Exception {
        mockMvc.perform(get("/all-offers")).
                andExpect(status().isOk()).
                andExpect(view().name("all-offers"));
    }
}
