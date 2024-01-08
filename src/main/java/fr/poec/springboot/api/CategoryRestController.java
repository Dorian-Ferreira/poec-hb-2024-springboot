package fr.poec.springboot.api;

import fr.poec.springboot.custom_response.ApiResponse;
import fr.poec.springboot.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/category")
public class CategoryRestController {

    private CategoryService categoryService;

    @GetMapping
    @Operation(summary = "Get a list of Category", description = "Returns all Category of the application")
    public ApiResponse list() {
        return this.categoryService.findAll();
    }

    @GetMapping(path = "/{field}")
    @Operation(summary = "Get a Category by id or slug", description = "Returns a Category as per the id or slug")
    public ApiResponse show(@PathVariable String field) {
        return this.categoryService.show(field);
    }

}