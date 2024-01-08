package fr.poec.springboot.api;

import fr.poec.springboot.DTO.CountryDTO;
import fr.poec.springboot.DTO.UserPostDTO;
import fr.poec.springboot.DTO.UserPutDTO;
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = CountryCustomApiResponse.class))}),
            @ApiResponse(responseCode = "204", description = "KO", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorCustomApiResponse.class))})
    })
    public CustomApiResponse list() {
        return this.countryService.findAll();
    }

    @GetMapping(path = "/{field}")
    @Operation(summary = "Get a Country by id or slug", description = "Returns a Country as per the id or slug")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = CountryCustomApiResponse.class))}),
            @ApiResponse(responseCode = "204", description = "KO", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorCustomApiResponse.class))})
    })
    public CustomApiResponse show(@PathVariable String field) {
        return this.countryService.show(field);
    }

    @PostMapping
    @Operation(summary = "Add a Country", description = "Returns the added Country")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = CountryCustomApiResponse.class))}),
            @ApiResponse(responseCode = "400", description = "KO", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorCustomApiResponse.class))}),
    })
    public CustomApiResponse create(@Valid @RequestBody CountryDTO countryDTO) {
        return countryService.create(countryDTO, null);
    }

    @PutMapping(path = "/{id}")
    @Operation(summary = "Modify a Country", description = "Returns the modified Country")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = CountryCustomApiResponse.class))}),
            @ApiResponse(responseCode = "400", description = "KO", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorCustomApiResponse.class))}),
    })
    public CustomApiResponse update(@Valid @RequestBody CountryDTO countryDTO, @PathVariable Long id) {
        return countryService.create(countryDTO, id);
    }
}
