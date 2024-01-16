package fr.poec.springboot.instant_faking.repository;

import fr.poec.springboot.instant_faking.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> getTop5ByOrderByCreatedAtDesc();
}