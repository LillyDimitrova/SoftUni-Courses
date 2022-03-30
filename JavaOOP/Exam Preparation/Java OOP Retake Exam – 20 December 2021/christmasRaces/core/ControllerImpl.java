package christmasRaces.core;

import christmasRaces.common.ExceptionMessages;
import christmasRaces.common.OutputMessages;
import christmasRaces.core.interfaces.Controller;
import christmasRaces.entities.cars.Car;
import christmasRaces.entities.cars.MuscleCar;
import christmasRaces.entities.cars.SportsCar;
import christmasRaces.entities.drivers.Driver;
import christmasRaces.entities.drivers.DriverImpl;
import christmasRaces.entities.races.Race;
import christmasRaces.entities.races.RaceImpl;
import christmasRaces.repositories.interfaces.Repository;


import java.util.Collection;

import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private Repository<Driver> driverRepository;
    private Repository<Car> carRepository;
    private Repository<Race> raceRepository;

    public ControllerImpl(Repository<Driver> driverRepository, Repository<Car> carRepository, Repository<Race> raceRepository) {
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
        this.raceRepository = raceRepository;
    }


    @Override
    public String createDriver(String driver) {
        if (this.driverRepository.getAll().contains(driver)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_EXISTS, driver));
        }
        Driver createDriver = new DriverImpl(driver);
        driverRepository.add(createDriver);
        return String.format(OutputMessages.DRIVER_CREATED,driver);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        Car car = null;
        String currentType = "";
        switch (type) {
            case "Muscle":
                car = new MuscleCar(model, horsePower);
                currentType = "MuscleCar";
                break;
            case "Sports":
                car = new SportsCar(model,horsePower);
                currentType = "SportsCar";
                break;
        }
        if (carRepository.getAll().contains(car)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.CAR_EXISTS, model));
        }
            carRepository.add(car);

        return String.format(OutputMessages.CAR_CREATED, currentType, model);
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        Driver driver = driverRepository.getByName(driverName);
        if (driver == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_FOUND, driverName));
        }
        Car car = carRepository.getByName(carModel);
        if (car == null) {
            throw new IllegalArgumentException(ExceptionMessages.CAR_NOT_FOUND);
        }
        driver.addCar(car);
        return String.format(OutputMessages.CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        Race race = raceRepository.getByName(raceName);
        if (race == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_NOT_FOUND, raceName));
        }
        Driver driver = driverRepository.getByName(driverName);
        if (driver == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_FOUND, driverName));
        }
        race.addDriver(driver);
        return String.format(OutputMessages.DRIVER_ADDED, driverName, raceName);
    }

    @Override
    public String startRace(String raceName) {
        Race race = raceRepository.getByName(raceName);
        if (race == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_NOT_FOUND, raceName));
        }
        int driversInTheRace = race.getDrivers().size();
        if (driversInTheRace < 3) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_INVALID, raceName, 3));
        }
        int laps = race.getLaps();
        Collection<Driver> drivers = race.getDrivers();

        List<Driver> fastestThree = drivers
                .stream()
                .sorted((d2, d1) -> Double.compare(d1.getCar().calculateRacePoints(laps),
                        d2.getCar().calculateRacePoints(laps)))
                .limit(3)
                .collect(Collectors.toList());

        this.raceRepository.remove(race);

        Driver first = fastestThree.get(0);
        Driver second = fastestThree.get(1);
        Driver third = fastestThree.get(2);

        StringBuilder message = new StringBuilder();

        String nameRace = race.getName();

        message.append(String.format(OutputMessages.DRIVER_FIRST_POSITION, first.getName(), nameRace))
                .append(System.lineSeparator());
        message.append(String.format(OutputMessages.DRIVER_SECOND_POSITION, second.getName(), nameRace))
                .append(System.lineSeparator());
        message.append(String.format(OutputMessages.DRIVER_THIRD_POSITION, third.getName(), nameRace))
                .append(System.lineSeparator());

        return message.toString().trim();
    }

    @Override
    public String createRace(String name, int laps) {
        Race race = this.raceRepository.getByName(name);
        if (race != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_EXISTS, name));
        }
        Race newRace = new RaceImpl(name,laps);
        raceRepository.add(newRace);
        return String.format(OutputMessages.RACE_CREATED, name);
    }
}
