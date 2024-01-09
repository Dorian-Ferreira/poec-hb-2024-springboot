package fr.poec.springboot.service;

import fr.poec.springboot.DTO.ReviewDTO;
import fr.poec.springboot.custom_response.CustomApiResponse;
import fr.poec.springboot.custom_response.ErrorCustomApiResponse;
import fr.poec.springboot.custom_response.ReviewCustomApiResponse;
import fr.poec.springboot.entity.Review;
import fr.poec.springboot.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ReviewService {

    private ReviewRepository reviewRepository;
    private GameService gameService;
    private UserService userService;

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

    public CustomApiResponse persist(ReviewDTO reviewDTO) {
        ReviewCustomApiResponse apiResponse = new ReviewCustomApiResponse();

        apiResponse.setEntity(Review.class.getSimpleName());
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.addObject(reviewRepository.saveAndFlush(reviewFromDTO(reviewDTO)));

        return apiResponse;
    }

    private Review reviewFromDTO(ReviewDTO reviewDTO) {
        Review review = new Review();

        review.setTitle(reviewDTO.getTitle());
        review.setContent(reviewDTO.getContent());
        review.setCreatedAt(new Date());

        review.setGame(gameService.getById(reviewDTO.getGameId()).get());
        review.setUser(userService.getById(reviewDTO.getUserId()).get());

        review.setRating(reviewDTO.getRating());
        review.setDownVote(0);
        review.setUpVote(0);

        return review;
    }
}
