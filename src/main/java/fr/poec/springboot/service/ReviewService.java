package fr.poec.springboot.service;

import fr.poec.springboot.custom_response.ApiResponse;
import fr.poec.springboot.custom_response.ReviewApiResponse;
import fr.poec.springboot.entity.Review;
import fr.poec.springboot.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewService {

    private ReviewRepository reviewRepository;

    public ApiResponse findAll() {
        ReviewApiResponse apiResponse = new ReviewApiResponse();

        apiResponse.setEntity(Review.class.getSimpleName());
        List<Review> reviews = reviewRepository.findAll();
        if(reviews.isEmpty()){
            apiResponse.setCode(HttpStatus.NO_CONTENT.value());
            apiResponse.addObject("There is no reviews.");
        } else {
            apiResponse.setCode(HttpStatus.OK.value());
            apiResponse.setObjects(new ArrayList<>(reviews));
        }

        return apiResponse;
    }
}
