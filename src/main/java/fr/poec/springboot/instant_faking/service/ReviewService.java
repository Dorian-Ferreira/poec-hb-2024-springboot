package fr.poec.springboot.instant_faking.service;

import fr.poec.springboot.instant_faking.DTO.ReviewDTO;
import fr.poec.springboot.instant_faking.entity.Game;
import fr.poec.springboot.instant_faking.entity.Review;
import fr.poec.springboot.instant_faking.entity.User;
import fr.poec.springboot.instant_faking.exception.NotFoundInstantFakingException;
import fr.poec.springboot.instant_faking.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewService implements DAOServiceInterface<Review> {

    private ReviewRepository reviewRepository;

    private UserService userService;

    private GameService gameService;

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review getObjectById(Long id) {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        if (optionalReview.isEmpty()) {
            throw new NotFoundInstantFakingException("Review", "id", id);
        }
        return optionalReview.get();
    }

    public Review persist(ReviewDTO reviewDTO) {
        Review review = new Review();
        review.setGame(gameService.getObjectById(reviewDTO.getGameId()));
        review.setUser(userService.getObjectById(reviewDTO.getUserId()));
        review.setContent(reviewDTO.getContent());
        review.setTitle(reviewDTO.getTitle());
        review.setRating(reviewDTO.getRating());
        return reviewRepository.saveAndFlush(review);
    }

}
