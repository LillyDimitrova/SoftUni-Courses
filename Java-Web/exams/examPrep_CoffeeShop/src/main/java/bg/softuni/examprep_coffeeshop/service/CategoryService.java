package bg.softuni.examprep_coffeeshop.service;

import bg.softuni.examprep_coffeeshop.model.entity.Category;
import bg.softuni.examprep_coffeeshop.model.enums.CategoryNameEnum;
import bg.softuni.examprep_coffeeshop.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public void initCategories() {

        if (categoryRepository.count() != 0) {
            return;
        }

        Arrays.stream(CategoryNameEnum.values()).
                forEach(categoryNameEnum -> {
                    Category category = new Category();
                    category.setName(categoryNameEnum);
                    switch (categoryNameEnum) {
                        case CAKE:
                            category.setNeededTime(10);
                            break;
                        case DRINK:
                            category.setNeededTime(1);
                            break;
                        case COFFEE:
                            category.setNeededTime(2);
                            break;
                        case OTHER:
                            category.setNeededTime(5);
                            break;
                    }
                    categoryRepository.save(category);
                });

    }

    public Category findByName(CategoryNameEnum category) {
        return categoryRepository.findByName(category).orElse(null);
    }
}
