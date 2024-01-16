package fr.poec.springboot.instant_faking.rest_controller;

import fr.poec.springboot.instant_faking.DTO.CategoryDTO;
import fr.poec.springboot.instant_faking.entity.Category;
import fr.poec.springboot.instant_faking.service.CategoryService;
import fr.poec.springboot.instant_faking.validator.group.ValidationGroup;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/api/category")
public class CategoryRestController {

    private CategoryService categoryService;

    @GetMapping
    public List<Category> list() {
        return this.categoryService.findAll();
    }

    @GetMapping(path = "/{field}")
    public Optional<Category> show(@PathVariable String field) {
        return categoryService.findByField(field);
    }

    @PostMapping
    @Validated(ValidationGroup.OnPostItem.class)
    public Category persist(@Valid @RequestBody CategoryDTO categoryDTO) {
        return categoryService.persist(categoryDTO, null);
    }

    @PutMapping("/{id}")
    @Validated(ValidationGroup.OnPutItem.class)
    public Category persist(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Long id) {
        return categoryService.persist(categoryDTO, id);
    }

}