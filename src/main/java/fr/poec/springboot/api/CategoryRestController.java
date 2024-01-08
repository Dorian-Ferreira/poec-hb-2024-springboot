package fr.poec.springboot.api;

import fr.poec.springboot.custom_response.CustomApiResponse;
import fr.poec.springboot.custom_response.CategoryCustomApiResponse;
import fr.poec.springboot.custom_response.ErrorCustomApiResponse;
import fr.poec.springboot.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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

}