package bg.softuni.mobilelele.model.dto;

import java.util.ArrayList;
import java.util.List;

public class BrandDTO {

    private List<ModelDTO> models;

    public List<ModelDTO> getModels() {
        return models;
    }

    public BrandDTO setModels(List<ModelDTO> models) {
        this.models = models;
        return this;
    }

    public BrandDTO addModel(ModelDTO model) {

        if(this.models == null) {
            this.models = new ArrayList<>();
        }
        this.models.add(model);
        return this;
    }
}
