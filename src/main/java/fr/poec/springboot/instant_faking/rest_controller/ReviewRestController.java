package fr.poec.springboot.instant_faking.rest_controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.poec.springboot.instant_faking.DTO.ReviewDTO;
import fr.poec.springboot.instant_faking.entity.Review;
import fr.poec.springboot.instant_faking.json_views.JsonViews;
import fr.poec.springboot.instant_faking.service.ReviewService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/review")
public class ReviewRestController {

    private ReviewService reviewService;

    @PostMapping
    @JsonView(JsonViews.ReviewShowView.class)
    public Review create(@Valid @RequestBody ReviewDTO reviewDTO) {
        return reviewService.persist(reviewDTO);
    }

}