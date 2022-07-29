package bg.softuni.FindYourHome.web;


import bg.softuni.FindYourHome.model.enums.CategoryEnum;
import bg.softuni.FindYourHome.model.enums.TypeHouseEnum;
import bg.softuni.FindYourHome.repository.OfferRepository;
import bg.softuni.FindYourHome.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class OfferControllerIT {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OfferRepository offerRepository;


    @Test
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"})
    void addOffer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/offer-add").
                        param("description", "Description test").
                        param("type", TypeHouseEnum.HOUSE.name()).
                        param("category", CategoryEnum.MULTIROOM.name()).
                        param("imageUrl", "http://example.com/image.png").
                        param("price", "100000").
                        param("yearOfConstruction", "2000-01-01").

                        with(csrf())).
                andExpect(status().is3xxRedirection());

        Assertions.assertEquals(1, offerRepository.count());
    }


}
