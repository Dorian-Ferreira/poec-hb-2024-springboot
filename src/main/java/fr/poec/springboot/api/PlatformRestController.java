package fr.poec.springboot.api;

import fr.poec.springboot.custom_response.CategoryCustomApiResponse;
import fr.poec.springboot.custom_response.CustomApiResponse;
import fr.poec.springboot.custom_response.ErrorCustomApiResponse;
import fr.poec.springboot.custom_response.PlatformCustomApiResponse;
import fr.poec.springboot.service.PlatformService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/platform")
public class PlatformRestController {

    private PlatformService platformService;

    @GetMapping
    @Operation(summary = "Get a list of Platform", description = "Returns all Platform of the application")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = PlatformCustomApiResponse.class))}),

            @ApiResponse(responseCode = "204", description = "KO", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorCustomApiResponse.class))})
    })
    public CustomApiResponse list() {
        return this.platformService.findAll();
    }

}