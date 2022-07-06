package bg.softuni.exam.service;

import bg.softuni.exam.model.entity.StyleEntity;
import bg.softuni.exam.model.enums.StyleNameEnum;
import bg.softuni.exam.repository.StyleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class StyleService {

    private final StyleRepository styleRepository;

    public StyleService(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    public void initStyle() {
        if (styleRepository.count() == 0) {
            for (StyleNameEnum c : StyleNameEnum.values()) {
                StyleEntity entity = new StyleEntity().setName(c);
                styleRepository.save(entity);
            }
        }

    }
}
