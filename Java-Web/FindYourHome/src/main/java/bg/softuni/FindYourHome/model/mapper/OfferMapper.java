package bg.softuni.FindYourHome.model.mapper;

import bg.softuni.FindYourHome.model.dtos.CreateOfferDTO;
import bg.softuni.FindYourHome.model.entity.OfferEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OfferMapper {

//    OfferEntity addOfferDtoToOfferEntity(CreateOfferDTO createOfferDTO);
    OfferEntity addOfferDtoToOfferEntity(CreateOfferDTO createOfferDTO);
}
