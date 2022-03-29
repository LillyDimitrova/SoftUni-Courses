package spaceStation.core;

import spaceStation.common.ConstantMessages;
import spaceStation.common.ExceptionMessages;
import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.astronauts.Biologist;
import spaceStation.models.astronauts.Geodesist;
import spaceStation.models.astronauts.Meteorologist;
import spaceStation.models.mission.Mission;
import spaceStation.models.mission.MissionImpl;
import spaceStation.models.planets.Planet;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private AstronautRepository astronautRepository;
    private PlanetRepository planetRepository;
    private int countExploredPlanets;

    public ControllerImpl() {
        this.astronautRepository = new AstronautRepository();
        this.planetRepository = new PlanetRepository();
    }

    @Override
    public String addAstronaut(String type, String astronautName) {
        Astronaut astronaut;
        switch (type) {
            case "Biologist":
                astronaut = new Biologist(astronautName);
                break;
            case "Geodesist":
                astronaut = new Geodesist(astronautName);
                break;
            case "Meteorologist":
                astronaut = new Meteorologist(astronautName);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.ASTRONAUT_INVALID_TYPE);
        }
        astronautRepository.add(astronaut);
        return String.format(ConstantMessages.ASTRONAUT_ADDED, type, astronautName);
    }

    @Override
    public String addPlanet(String planetName, String... items) {
        Planet planet = new PlanetImpl(planetName);
        planet.getItems().addAll(Arrays.asList(items));
        planetRepository.add(planet);
        return String.format(ConstantMessages.PLANET_ADDED,planetName);
    }

    @Override
    public String retireAstronaut(String astronautName) {
        Astronaut astronaut = astronautRepository.findByName(astronautName);
        if (astronaut == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.ASTRONAUT_DOES_NOT_EXIST,astronautName));
        }
        astronautRepository.remove(astronaut);
        return String.format(ConstantMessages.ASTRONAUT_RETIRED,astronautName);
    }

    @Override
    public String explorePlanet(String planetName) {
        List<Astronaut> suitableAstronauts = astronautRepository.getModels().stream().filter(a -> a.getOxygen() > 60).collect(Collectors.toList());
        if (suitableAstronauts.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.PLANET_ASTRONAUTS_DOES_NOT_EXISTS);
        }
        int countBeforeMissions = suitableAstronauts.size();
        Planet planet = planetRepository.findByName(planetName);
        Mission mission = new MissionImpl();
        mission.explore(planet,suitableAstronauts);
        countExploredPlanets++;
        int countAfterMission = suitableAstronauts.size();
        return String.format(ConstantMessages.PLANET_EXPLORED,planetName, countBeforeMissions - countAfterMission);
    }

    @Override
    public String report() {
        StringBuilder output = new StringBuilder();
        output.append(String.format(ConstantMessages.REPORT_PLANET_EXPLORED,countExploredPlanets)).append("\n");
        output.append(ConstantMessages.REPORT_ASTRONAUT_INFO).append("\n");
        for (Astronaut a : this.astronautRepository.getModels()) {
            output.append(String.format(ConstantMessages.REPORT_ASTRONAUT_NAME,a.getName())).append("\n");
            output.append(String.format(ConstantMessages.REPORT_ASTRONAUT_OXYGEN, a.getOxygen())).append("\n");
            if (a.getBag().getItems().size() == 0) {
                output.append(String.format(ConstantMessages.REPORT_ASTRONAUT_BAG_ITEMS, "none")).append("\n");
            } else {
                output.append(String.format(ConstantMessages.REPORT_ASTRONAUT_BAG_ITEMS, String.join(", ", a.getBag().getItems())));
            }
        }
        return output.toString().trim();
    }
}
