package bg.softuni.FindYourHome.service;

import bg.softuni.FindYourHome.model.entity.CityEntity;
import bg.softuni.FindYourHome.repository.CitiesRepository;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    private final CitiesRepository citiesRepository;

    public CityService(CitiesRepository citiesRepository) {
        this.citiesRepository = citiesRepository;
    }


    public CityEntity getCityByName(String city) {
        CityEntity currentCity = citiesRepository.getByName(city).orElse(null);
        if (currentCity == null) {
           currentCity =  citiesRepository.save(new CityEntity().setName(city));
        }

        return currentCity;
    }
}
