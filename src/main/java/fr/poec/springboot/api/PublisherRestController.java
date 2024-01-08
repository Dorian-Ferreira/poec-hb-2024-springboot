package fr.poec.springboot.api;

import com.fasterxml.jackson.annotation.JsonView;
import fr.poec.springboot.custom_response.ApiResponse;
import fr.poec.springboot.json_view.JsonViews;
import fr.poec.springboot.service.PublisherService;
import io.swagger.v3.oas.annotations.Operation;
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
    public ApiResponse show(@PathVariable String field) {
        return publisherService.show(field);
    }

}