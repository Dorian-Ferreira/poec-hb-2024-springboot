package fr.poec.springboot.api;

import com.fasterxml.jackson.annotation.JsonView;
import fr.poec.springboot.DTO.ReviewDTO;
import fr.poec.springboot.custom_response.*;
import fr.poec.springboot.json_view.JsonViews;
import fr.poec.springboot.service.ReviewService;
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
@RequestMapping("/api/review")
public class ReviewRestController {

    private ReviewService reviewService;

    @GetMapping
    @JsonView(JsonViews.ReviewListView.class)
    @Operation(summary = "Get a list of Review", description = "Returns all Review of the application")
    public CustomApiResponse list() {
        return reviewService.findAll();
    }

    @PostMapping
    @Operation(summary = "Add a Review", description = "Returns the added Review")
    @JsonView(JsonViews.ReviewListView.class)
    public CustomApiResponse create(@Valid @RequestBody ReviewDTO reviewDTO) {
        return reviewService.persist(reviewDTO);
    }
}