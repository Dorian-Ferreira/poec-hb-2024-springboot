package fr.poec.springboot.api;

import fr.poec.springboot.DTO.CategoryDTO;
import fr.poec.springboot.custom_response.CountryCustomApiResponse;
import fr.poec.springboot.custom_response.CustomApiResponse;
import fr.poec.springboot.custom_response.CategoryCustomApiResponse;
import fr.poec.springboot.custom_response.ErrorCustomApiResponse;
import fr.poec.springboot.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/category")
public class CategoryRestController {

    private CategoryService categoryService;

    @GetMapping
    @Operation(summary = "Get a list of Category", description = "Returns all Category of the application")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = CategoryCustomApiResponse.class))}),

            @ApiResponse(responseCode = "204", description = "KO", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorCustomApiResponse.class))})
    })
    public CustomApiResponse list() {
        return this.categoryService.findAll();
    }

    @GetMapping(path = "/{field}")
    @Operation(summary = "Get a Category by id or slug", description = "Returns a Category as per the id or slug")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = CategoryCustomApiResponse.class))}),

            @ApiResponse(responseCode = "204", description = "KO", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorCustomApiResponse.class))})
    })
    public CustomApiResponse show(@PathVariable String field) {
        return this.categoryService.show(field);
    }

    @PostMapping
    @Operation(summary = "Add a Category", description = "Returns the added Category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = CountryCustomApiResponse.class))}),
            @ApiResponse(responseCode = "400", description = "KO", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorCustomApiResponse.class))}),
    })
    public CustomApiResponse create(@Valid @RequestBody CategoryDTO categoryDTO) {
        return categoryService.persist(categoryDTO, null);
    }

    @PutMapping(path = "/{id}")
    @Operation(summary = "Modify a Category", description = "Returns the modified Category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = CountryCustomApiResponse.class))}),
    })
    public CustomApiResponse update(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Long id) {
        return categoryService.persist(categoryDTO, id);
    }
}