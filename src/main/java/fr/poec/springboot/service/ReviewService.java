package fr.poec.springboot.service;

import fr.poec.springboot.custom_response.CustomApiResponse;
import fr.poec.springboot.custom_response.ErrorCustomApiResponse;
import fr.poec.springboot.custom_response.ReviewCustomApiResponse;
import fr.poec.springboot.entity.Review;
import fr.poec.springboot.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ReviewService {

    private ReviewRepository reviewRepository;

    public CustomApiResponse findAll() {
        ReviewCustomApiResponse apiResponse = new ReviewCustomApiResponse();
        ErrorCustomApiResponse errorApiResponse = new ErrorCustomApiResponse();

        apiResponse.setEntity(Review.class.getSimpleName());
        errorApiResponse.setEntity(Review.class.getSimpleName());

        List<Review> reviews = reviewRepository.findAll();
        if(reviews.isEmpty()){
            errorApiResponse.setCode(HttpStatus.NO_CONTENT.value());
            errorApiResponse.setMessage("There is no reviews.");

            return errorApiResponse;
        }

        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setObjects(reviews);

        return apiResponse;
    }
}
