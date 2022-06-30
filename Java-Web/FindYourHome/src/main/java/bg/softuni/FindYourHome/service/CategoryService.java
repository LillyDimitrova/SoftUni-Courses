package bg.softuni.FindYourHome.service;

import bg.softuni.FindYourHome.model.entity.CategoryEntity;
import bg.softuni.FindYourHome.model.enums.CategoryEnum;
import bg.softuni.FindYourHome.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void initCategories() {
        if(categoryRepository.count() == 0) {
            for ( CategoryEnum category : CategoryEnum.values()) {
                CategoryEntity categoryEntity = new CategoryEntity().setCategory(category);
                categoryRepository.save(categoryEntity);
            }
        }
    }
}
