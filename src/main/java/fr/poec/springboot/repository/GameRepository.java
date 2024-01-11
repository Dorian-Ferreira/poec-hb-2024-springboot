package fr.poec.springboot.repository;

import fr.poec.springboot.entity.Category;
import fr.poec.springboot.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>, NameRepository<Game> {

    Optional<Game> findBySlug(String field);
    List<Game> findTop9ByOrderByPublishedAtDesc();

}
