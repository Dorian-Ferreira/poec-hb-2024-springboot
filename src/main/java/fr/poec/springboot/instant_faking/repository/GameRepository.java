package fr.poec.springboot.instant_faking.repository;

import fr.poec.springboot.instant_faking.entity.Category;
import fr.poec.springboot.instant_faking.entity.Game;
import fr.poec.springboot.instant_faking.entity.Publisher;
import org.hibernate.query.spi.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository
            extends JpaRepository<Game, Long>,
                    EntityNameRepository<Game> {

    // SELECT * FROM game WHERE price BETWEEN min AND max
    List<Game> findAllByPriceBetween(double min, double max);

    List<Game> findTop9ByOrderByPublishedAtDesc();

    // SELECT * FROM game WHERE name LIKE "%{name}%"
    List<Game> findAllByNameIsContainingIgnoreCase(String name);

    // SELECT * FROM game WHERE name LIKE "{name}%"
    List<Game> findAllByNameIsStartingWithIgnoreCase(String name);

    // SELECT * FROM game WHERE game.publisher_id = {p.id}
    List<Game> findAllByPublisher(Publisher p);

    // SELECT * FROM game g
    // JOIN game_category gc ON g.id = gc.game_id
    // JOIN category c ON c.id = gc.category_id
    // WHERE c.slug = {slug}
    // ORDER BY published_at DESC
    List<Game> findAllByCategoriesSlugOrderByPublishedAtDesc(String slug);

    // SELECT * FROM game g
    // JOIN game_category gc ON g.id = gc.game_id
    // WHERE gc.category_id = {c.id}
    // ORDER BY published_at DESC
    List<Game> findAllByCategoriesOrderByPublishedAtDesc(Category c);

    Optional<Game> findBySlug(String slug);

    List<Game> findAllByNameIsContainingIgnoreCaseOrCategoriesNameIsContainingIgnoreCaseOrPlatformsNameIsContainingIgnoreCaseOrCountriesNameIsContainingIgnoreCaseOrderByPriceDesc(String s1, String s2, String s3, String s4);

}