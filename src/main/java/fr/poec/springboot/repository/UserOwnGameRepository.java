package fr.poec.springboot.repository;

import fr.poec.springboot.entity.UserOwnGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserOwnGameRepository extends JpaRepository<UserOwnGame, Long> {
    boolean existsByUserIdAndGameId(Long userId, Long gameId);
}
