package fr.poec.springboot.instant_faking.repository;

import fr.poec.springboot.instant_faking.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}