package fr.poec.springboot.api;

import com.fasterxml.jackson.annotation.JsonView;
import fr.poec.springboot.custom_response.ApiResponse;
import fr.poec.springboot.json_view.JsonViews;
import fr.poec.springboot.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/review")
public class ReviewRestController {

    private ReviewService reviewService;

    @GetMapping
    @JsonView(JsonViews.ReviewListView.class)
    @Operation(summary = "Get a list of Review", description = "Returns all Review of the application")
    public ApiResponse list() {
        return reviewService.findAll();
    }

}