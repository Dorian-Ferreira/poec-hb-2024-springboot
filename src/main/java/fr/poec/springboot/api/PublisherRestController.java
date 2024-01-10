package fr.poec.springboot.api;

import com.fasterxml.jackson.annotation.JsonView;
import fr.poec.springboot.DTO.PublisherDTO;
import fr.poec.springboot.custom_response.*;
import fr.poec.springboot.json_view.JsonViews;
import fr.poec.springboot.service.PublisherService;
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
@RequestMapping("/api/publisher")
public class PublisherRestController {

    private PublisherService publisherService;

    @GetMapping(path = "/{field}")
    @JsonView(JsonViews.PublisherShowView.class)
    @Operation(summary = "Get a Publisher by id or slug", description = "Returns a Publisher as per the id or slug")
    public CustomApiResponse show(@PathVariable String field) {
        return publisherService.show(field);
    }

    @PostMapping
    @JsonView(JsonViews.PublisherShowView.class)
    @Operation(summary = "Add a Publisher", description = "Returns the added Publisher")
    public CustomApiResponse create(@Valid @RequestBody PublisherDTO publisherDTO) {
        return publisherService.persist(publisherDTO, null);
    }

    @PutMapping(path = "/{id}")
    @JsonView(JsonViews.PublisherShowView.class)
    @Operation(summary = "Modify a Publisher", description = "Returns the modified Publisher")
    public CustomApiResponse update(@Valid @RequestBody PublisherDTO publisherDTO, @PathVariable Long id) {
        return publisherService.persist(publisherDTO, id);
    }
}