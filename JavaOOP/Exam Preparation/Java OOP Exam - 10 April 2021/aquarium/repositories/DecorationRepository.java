package aquarium.repositories;

import aquarium.entities.decorations.Decoration;

import java.util.*;

public class DecorationRepository implements Repository{
    private List<Decoration> decorations;

    public DecorationRepository() {
        this.decorations = new ArrayList<>();
    }

    @Override
    public void add(Decoration decoration) {
        decorations.add(decoration);
    }

    @Override
    public boolean remove(Decoration decoration) {
        return decorations.remove(decoration);
    }

    @Override
    public Decoration findByType(String type) {
        Decoration decoration = null;
        for (Decoration d : decorations) {
            if (d.getClass().getSimpleName().equals(type)) {
                decoration = d;
            }
        }
        return decoration;
    }
}
