package bg.softuni.FindYourHome.model.mapper;

import bg.softuni.FindYourHome.model.dtos.CreateOfferDTO;
import bg.softuni.FindYourHome.model.entity.OfferEntity;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;
import java.time.Instant;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2022-06-16T11:24:39+0300",
        comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.1.jar, environment: Java 11.0.15 (Oracle Corporation)"
)

@Component
public class OfferMapperImpl implements OfferMapper {
    @Override
    public OfferEntity addOfferDtoToOfferEntity(CreateOfferDTO createOfferDTO) {
        if (createOfferDTO == null) {
            return null;
        }
        OfferEntity offerEntity = new OfferEntity();

        offerEntity.setTypeHouse(createOfferDTO.getType()).
                setCategory(createOfferDTO.getCategory()).
                setDescription(createOfferDTO.getDescription()).
                setPrice(createOfferDTO.getPrice()).
                setYearOfConstruction(createOfferDTO.getYearOfConstruction()).
                setInstant(Instant.now()).
                setImageUrl("https://images.pexels.com/photos/106399/pexels-photo-106399.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");

        return offerEntity;
    }
}
