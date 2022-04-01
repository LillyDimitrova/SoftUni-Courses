package glacialExpedition.models.mission;

import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.states.State;


import java.util.Collection;
import java.util.List;

public class MissionImpl implements Mission{

    @Override
    public void explore(State state, List<Explorer> explorers) {
        Collection <String> stateEx = state.getExhibits();
        for (Explorer e : explorers ) {

            while (e.canSearch() && stateEx.iterator().hasNext()) {
                e.search();
                String currentElement = state.getExhibits().iterator().next();
                e.getSuitcase().getExhibits().add(currentElement);
                state.getExhibits().remove(currentElement);
            }
        }
    }
}
