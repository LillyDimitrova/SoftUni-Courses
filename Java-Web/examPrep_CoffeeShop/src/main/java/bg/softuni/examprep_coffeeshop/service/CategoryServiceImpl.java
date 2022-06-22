package bg.softuni.examprep_coffeeshop.service;

import bg.softuni.examprep_coffeeshop.model.Category;
import bg.softuni.examprep_coffeeshop.model.CategoryNameEnum;
import bg.softuni.examprep_coffeeshop.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
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
}
