package fr.poec.springboot.instant_faking.service;

import fr.poec.springboot.instant_faking.DTO.CategoryDTO;
import fr.poec.springboot.instant_faking.entity.Category;
import fr.poec.springboot.instant_faking.exception.NotFoundInstantFakingException;
import fr.poec.springboot.instant_faking.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService implements DAOServiceInterface<Category> {

    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getObjectById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            throw new NotFoundInstantFakingException("Category", "id", id);
        }
        return optionalCategory.get();
    }

    public Optional<Category> findByField(String field) {
        try {
            Long id = Long.parseLong(field);
            return categoryRepository.findById(id);
        } catch (NumberFormatException e) {
            return categoryRepository.findBySlug(field);
        }
    }

    public Category persist(CategoryDTO categoryDTO, Long id) {
        if (id != null && categoryRepository.findById(id).isEmpty()) {
            throw new NotFoundInstantFakingException(
                    "Category", "id", id
            );
        }

        Category category = new Category();
        category.setId(id);
        category.setName(categoryDTO.getName());
        category.setImage(categoryDTO.getImage());
        return categoryRepository.saveAndFlush(category);
    }
}
