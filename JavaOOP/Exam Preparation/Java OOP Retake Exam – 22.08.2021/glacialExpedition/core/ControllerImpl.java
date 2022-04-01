package glacialExpedition.core;

import glacialExpedition.common.ConstantMessages;
import glacialExpedition.common.ExceptionMessages;
import glacialExpedition.models.explorers.AnimalExplorer;
import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.explorers.GlacierExplorer;
import glacialExpedition.models.explorers.NaturalExplorer;
import glacialExpedition.models.mission.Mission;
import glacialExpedition.models.mission.MissionImpl;
import glacialExpedition.models.states.State;
import glacialExpedition.models.states.StateImpl;
import glacialExpedition.repositories.ExplorerRepository;
import glacialExpedition.repositories.Repository;
import glacialExpedition.repositories.StateRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private Repository<Explorer> explorerRepository;
    private Repository<State> stateRepository;
    private int exploredStatesCount;

    public ControllerImpl() {
        this.explorerRepository = new ExplorerRepository();
        this.stateRepository = new StateRepository();
    }

    @Override
    public String addExplorer(String type, String explorerName) {
        Explorer explorer = null;
        switch (type) {
            case "AnimalExplorer":
                explorer = new AnimalExplorer(explorerName);
                break;
            case "GlacierExplorer":
                explorer = new GlacierExplorer(explorerName);
                break;
            case "NaturalExplorer":
                explorer = new NaturalExplorer(explorerName);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.EXPLORER_INVALID_TYPE);
        }
        explorerRepository.add(explorer);
        return String.format(ConstantMessages.EXPLORER_ADDED, type, explorerName);
    }

    @Override
    public String addState(String stateName, String... exhibits) {
        List<String> currentExhibits = Arrays.asList(exhibits);
        State state = new StateImpl(stateName);
        state.getExhibits().addAll(currentExhibits);
        stateRepository.add(state);
        return String.format(ConstantMessages.STATE_ADDED, stateName);
    }

    @Override
    public String retireExplorer(String explorerName) {
        Explorer explorer = explorerRepository.byName(explorerName);
        if (explorer == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.EXPLORER_DOES_NOT_EXIST,explorerName));
        }
        explorerRepository.remove(explorer);

        return String.format(ConstantMessages.EXPLORER_RETIRED,explorerName);
    }

    @Override
    public String exploreState(String stateName) {
        List<Explorer> suitableExplorers = explorerRepository.getCollection().stream().filter(e -> e.getEnergy() > 50).collect(Collectors.toList());
        if (suitableExplorers.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.STATE_EXPLORERS_DOES_NOT_EXISTS);
        }
        State state = stateRepository.byName(stateName);
        Mission mission = new MissionImpl();
        mission.explore(state,suitableExplorers);
        exploredStatesCount++;
        long retiredExplorers = suitableExplorers.stream().filter(e -> e.getEnergy() == 0).count();
        return String.format(ConstantMessages.STATE_EXPLORER,stateName, retiredExplorers);
    }

    @Override
    public String finalResult() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format(ConstantMessages.FINAL_STATE_EXPLORED, exploredStatesCount)).append("\n");
        builder.append(ConstantMessages.FINAL_EXPLORER_INFO).append("\n");
        for (Explorer explorer : explorerRepository.getCollection()) {
            builder.append(String.format(ConstantMessages.FINAL_EXPLORER_NAME, explorer.getName())).append("\n");
            builder.append(String.format(ConstantMessages.FINAL_EXPLORER_ENERGY, explorer.getEnergy())).append("\n");
            if (explorer.getSuitcase().getExhibits().isEmpty()) {
                builder.append(String.format(ConstantMessages.FINAL_EXPLORER_SUITCASE_EXHIBITS,"None")).append("\n");
            } else {
                builder.append(String.format(ConstantMessages.FINAL_EXPLORER_SUITCASE_EXHIBITS,String.join(", ",explorer.getSuitcase().getExhibits()))).append("\n");
            }

        }
        return builder.toString().trim();
    }
}
