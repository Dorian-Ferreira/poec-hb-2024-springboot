package fr.poec.springboot.service;

import fr.poec.springboot.custom_response.ApiResponse;
import fr.poec.springboot.custom_response.CategoryApiResponse;
import fr.poec.springboot.entity.Category;
import fr.poec.springboot.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    public ApiResponse findAll() {
        CategoryApiResponse apiResponse = new CategoryApiResponse();

        apiResponse.setEntity(Category.class.getSimpleName());
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty()){
            apiResponse.setCode(HttpStatus.NO_CONTENT.value());
            apiResponse.addObject("There is no categories.");
        } else {
            apiResponse.setCode(HttpStatus.OK.value());
            apiResponse.setObjects(new ArrayList<>(categories));
        }

        return apiResponse;
    }

    public ApiResponse show(String field) {
        CategoryApiResponse apiResponse = new CategoryApiResponse();

        apiResponse.setEntity(Category.class.getSimpleName());

        Optional<Category> category;

        try {
            Long id = Long.parseLong(field);
            category = categoryRepository.findById(id);
        } catch (NumberFormatException e) {
            category = categoryRepository.findBySlug(field);
        }

        if(category.isPresent()){
            apiResponse.setCode(HttpStatus.OK.value());
            apiResponse.setObjects(Collections.singletonList(category.get()));
        } else {
            apiResponse.setCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
            apiResponse.addObject("This category doesn't exist.");
        }

        return apiResponse;
    }
}
