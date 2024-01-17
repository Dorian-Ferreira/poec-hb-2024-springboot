package fr.poec.springboot.instant_faking.repository;

import fr.poec.springboot.instant_faking.entity.User;
import fr.poec.springboot.instant_faking.entity.UserOwnGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserOwnGameRepository extends JpaRepository<UserOwnGame, Long> {

    List<UserOwnGame> findTop5ByOrderByCreatedAtDesc();

    @Query("SELECT sum(g.price) FROM UserOwnGame uog JOIN Game g ON uog.game.id = g.id")
    long findTotalSales();
}