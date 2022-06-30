package bg.softuni.FindYourHome.service;

import bg.softuni.FindYourHome.model.dtos.CreateOfferDTO;
import bg.softuni.FindYourHome.repository.OfferRepository;
import org.springframework.stereotype.Service;

@Service
public class OfferService {
    private final OfferRepository offerRepository;

    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }


    public boolean createOffer(CreateOfferDTO createSongDTO) {
//        StyleEntity style = this.styleRepository.findByName(createSongDTO.getStyle()).orElse(null);
//        if (style == null) {
//            return false;
//        }
//
//        SongEntity song = new SongEntity()
//                .setDuration(createSongDTO.getDurationInSeconds())
//                .setPerformer(createSongDTO.getPerformer())
//                .setStyle(style)
//                .setReleaseDate(createSongDTO.getReleaseDate())
//                .setTitle(createSongDTO.getTitle());
//        songRepository.save(song);

        return true;
    }
}
