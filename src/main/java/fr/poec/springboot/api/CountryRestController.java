package fr.poec.springboot.api;

import fr.poec.springboot.custom_response.ApiResponse;
import fr.poec.springboot.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/country")
public class CountryRestController {

    private CountryService countryService;

    @GetMapping
    @Operation(summary = "Get a list of Country", description = "Returns all Country of the application")
    public ApiResponse list() {
        return this.countryService.findAll();
    }

    @GetMapping(path = "/{field}")
    @Operation(summary = "Get a Country by id or slug", description = "Returns a Country as per the id or slug")
    public ApiResponse show(@PathVariable String field) {
        return this.countryService.show(field);
    }

}
