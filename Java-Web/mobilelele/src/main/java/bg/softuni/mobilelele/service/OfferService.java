package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.dto.AddOfferDTO;
import bg.softuni.mobilelele.model.entity.ModelEntity;
import bg.softuni.mobilelele.model.entity.OfferEntity;
import bg.softuni.mobilelele.model.entity.UserEntity;
import bg.softuni.mobilelele.model.mapper.OfferMapper;
import bg.softuni.mobilelele.repository.ModelRepository;
import bg.softuni.mobilelele.repository.OfferRepository;
import bg.softuni.mobilelele.repository.UserRepository;
import bg.softuni.mobilelele.user.CurrentUser;
import org.springframework.stereotype.Service;

@Service
public class OfferService {
    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;
    private final UserRepository userRepository;
    private final CurrentUser currentUser;

    private final ModelRepository modelRepository;

    public OfferService(OfferRepository offerRepository, OfferMapper offerMapper, UserRepository userRepository, CurrentUser currentUser, ModelRepository modelRepository) {
        this.offerRepository = offerRepository;
        this.offerMapper = offerMapper;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.modelRepository = modelRepository;
    }

    public void addOffer(AddOfferDTO addOfferDTO) {
        OfferEntity newOffer = offerMapper.addOfferDtoToOfferEntity(addOfferDTO);

        //TODO

       UserEntity seller  =  userRepository.findByEmail(currentUser.getEmail()).orElseThrow();

        ModelEntity model = modelRepository.findById(addOfferDTO.getModelId()).orElseThrow();

        newOffer.setModel(model);
        newOffer.setSeller(seller);

        offerRepository.save(newOffer);
    }


}
