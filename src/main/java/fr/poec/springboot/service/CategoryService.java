package fr.poec.springboot.service;

import fr.poec.springboot.DTO.CategoryDTO;
import fr.poec.springboot.custom_response.CustomApiResponse;
import fr.poec.springboot.custom_response.CategoryCustomApiResponse;
import fr.poec.springboot.custom_response.ErrorCustomApiResponse;
import fr.poec.springboot.entity.Category;
import fr.poec.springboot.repository.CategoryRepository;
import fr.poec.springboot.utils.Slug;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CustomApiResponse findAll() {
        CategoryCustomApiResponse apiResponse = new CategoryCustomApiResponse();
        ErrorCustomApiResponse errorApiResponse = new ErrorCustomApiResponse();

        apiResponse.setEntity(Category.class.getSimpleName());
        errorApiResponse.setEntity(Category.class.getSimpleName());

        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty()) {
            errorApiResponse.setCode(HttpStatus.NO_CONTENT.value());
            errorApiResponse.setMessage("There is no categories.");
            return errorApiResponse;
        }
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setObjects(categories);

        return apiResponse;
    }

    public CustomApiResponse show(String field) {
        CategoryCustomApiResponse apiResponse = new CategoryCustomApiResponse();
        ErrorCustomApiResponse errorApiResponse = new ErrorCustomApiResponse();

        apiResponse.setEntity(Category.class.getSimpleName());
        errorApiResponse.setEntity(Category.class.getSimpleName());

        Optional<Category> category;

        try {
            Long id = Long.parseLong(field);
            category = getById(id);
        } catch (NumberFormatException e) {
            category = categoryRepository.findBySlug(field);
        }

        if(category.isPresent()){
            apiResponse.setCode(HttpStatus.OK.value());
            apiResponse.addObject(category.get());

            return apiResponse;
        }
        errorApiResponse.setCode(HttpStatus.NO_CONTENT.value());
        errorApiResponse.setMessage("There is no categories.");
        return errorApiResponse;
    }


    public CustomApiResponse persist(CategoryDTO categoryDTO, Long id) {
        CategoryCustomApiResponse apiResponse = new CategoryCustomApiResponse();
        ErrorCustomApiResponse errorApiResponse = new ErrorCustomApiResponse();

        apiResponse.setEntity(Category.class.getSimpleName());
        errorApiResponse.setEntity(Category.class.getSimpleName());

        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.addObject(categoryRepository.saveAndFlush(categoryFromDTO(categoryDTO, id)));

        return apiResponse;
    }

    private Category categoryFromDTO(CategoryDTO categoryDTO, Long id) {
        Category country = new Category();

        country.setId(id);
        country.setName(categoryDTO.getName());
        country.setImage(categoryDTO.getImage());

        return country;
    }

    public Optional<Category> getById(Long id) {
        return categoryRepository.findById(id);
    }
}
