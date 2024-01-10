package fr.poec.springboot.api;

import fr.poec.springboot.DTO.CountryDTO;
import fr.poec.springboot.custom_response.*;
import fr.poec.springboot.service.CountryService;
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
@RequestMapping("/api/country")
public class CountryRestController {

    private CountryService countryService;

    @GetMapping
    @Operation(summary = "Get a list of Country", description = "Returns all Country of the application")
    public CustomApiResponse list() {
        return this.countryService.findAll();
    }

    @GetMapping(path = "/{field}")
    @Operation(summary = "Get a Country by id or slug", description = "Returns a Country as per the id or slug")
    public CustomApiResponse show(@PathVariable String field) {
        return this.countryService.show(field);
    }

    @PostMapping
    @Operation(summary = "Add a Country", description = "Returns the added Country")
    public CustomApiResponse create(@Valid @RequestBody CountryDTO countryDTO) {
        return countryService.persist(countryDTO, null);
    }

    @PutMapping(path = "/{id}")
    @Operation(summary = "Modify a Country", description = "Returns the modified Country")
    public CustomApiResponse update(@Valid @RequestBody CountryDTO countryDTO, @PathVariable Long id) {
        return countryService.persist(countryDTO, id);
    }
}
