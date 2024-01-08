package fr.poec.springboot.api;

import com.fasterxml.jackson.annotation.JsonView;
import fr.poec.springboot.custom_response.CustomApiResponse;
import fr.poec.springboot.custom_response.ErrorCustomApiResponse;
import fr.poec.springboot.custom_response.PlatformCustomApiResponse;
import fr.poec.springboot.custom_response.PublisherCustomApiResponse;
import fr.poec.springboot.json_view.JsonViews;
import fr.poec.springboot.service.PublisherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/publisher")
public class PublisherRestController {

    private PublisherService publisherService;

    @GetMapping(path = "/{field}")
    @JsonView(JsonViews.PublisherShowView.class)
    @Operation(summary = "Get a Publisher by id or slug", description = "Returns a Publisher as per the id or slug")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = PublisherCustomApiResponse.class))}),

            @ApiResponse(responseCode = "422", description = "KO", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorCustomApiResponse.class))})
    })
    public CustomApiResponse show(@PathVariable String field) {
        return publisherService.show(field);
    }

}