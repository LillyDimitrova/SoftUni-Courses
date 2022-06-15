package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.dto.AddOfferDTO;
import bg.softuni.mobilelele.model.dto.BrandDTO;
import bg.softuni.mobilelele.model.entity.OfferEntity;
import bg.softuni.mobilelele.model.view.OfferSummaryView;
import bg.softuni.mobilelele.repository.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferService {
    private final OfferRepository offerRepository;

    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }


    public void initializeOffers() {
        //TODO
    }


    public List<OfferSummaryView> getAllOffers() {
        return offerRepository.findAll().stream().map(this::map).collect(Collectors.toList());
    }

    private OfferSummaryView map(OfferEntity offerEntity) {
        //TODO
        return new OfferSummaryView();
    }
    public void addOffer(AddOfferDTO addOfferDTO) {
        //TODO
    }

    public List<BrandDTO> getAllBrands() {
        //TODO
    }
}
