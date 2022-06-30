package bg.softuni.FindYourHome.service;

import bg.softuni.FindYourHome.repository.HouseRepository;
import org.springframework.stereotype.Service;

@Service
public class HouseService {

    private final HouseRepository houseRepository;

    public HouseService(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

}
