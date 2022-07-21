package bg.softuni.FindYourHome.model.mapper;

import bg.softuni.FindYourHome.model.dtos.CreateOfferDTO;
import bg.softuni.FindYourHome.model.dtos.OfferDetailDTO;
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
                setImageUrl(createOfferDTO.getImageUrl());

        return offerEntity;
    }

    @Override
    public OfferDetailDTO offerEntityToOfferDetailDTO(OfferEntity offer) {
        OfferDetailDTO offerDetailDTO = new OfferDetailDTO()
                .setCategory(offer.getCategory())
                .setDescription(offer.getDescription())
                .setImageUrl(offer.getImageUrl())
                .setPrice(offer.getPrice())
                .setTypeHouse(offer.getTypeHouse())
                .setYearOfConstruction(offer.getYearOfConstruction())
                .setImageUrl(offer.getImageUrl())
                .setId(offer.getId())
                .setSeller(offer.getSeller().getUsername());
        return offerDetailDTO;
    }
}
