package fr.poec.springboot.instant_faking.service;

import fr.poec.springboot.instant_faking.DTO.CountryDTO;
import fr.poec.springboot.instant_faking.DTO.GameDTO;
import fr.poec.springboot.instant_faking.entity.Country;
import fr.poec.springboot.instant_faking.entity.Game;
import fr.poec.springboot.instant_faking.exception.NotFoundInstantFakingException;
import fr.poec.springboot.instant_faking.repository.GameRepository;
import lombok.AllArgsConstructor;
import org.hibernate.query.spi.Limit;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GameService {

    private GameRepository gameRepository;

    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    public Game findBySlug(String slug) {
        Optional<Game> optionalGame = gameRepository.findBySlug(slug);
        if (optionalGame.isEmpty()) {
            throw new NotFoundInstantFakingException("Game", "slug", slug);
        }
        return optionalGame.get();
    }

    public List<Game> findTop9ByOrderByPublishedAtDesc() {
        return gameRepository.findTop9ByOrderByPublishedAtDesc();
    }

    public List<Game> findByCategory(String slug) {
        return gameRepository.findAllByCategoriesSlugOrderByPublishedAtDesc(slug);
    }

    public Game getObjectById(Long id) {
        Optional<Game> optionalGame = gameRepository.findById(id);
        if (optionalGame.isEmpty()) {
            throw new NotFoundInstantFakingException("Game", "id", id);
        }
        return optionalGame.get();
    }

    public List<Game> findAllBySearchedValue(String search) {
        return gameRepository.findAllByNameIsContainingIgnoreCaseOrPublisherNameIsContainingIgnoreCaseOrCategoriesNameIsContainingIgnoreCaseOrPlatformsNameIsContainingIgnoreCaseOrCountriesNameIsContainingIgnoreCaseOrderByPriceDesc(search, search, search, search, search);
    }

    public Game persist(GameDTO gameDTO, Long id) {
        if (id != null && gameRepository.findById(id).isEmpty()) {
            throw new NotFoundInstantFakingException(
                    "Game", "id", id
            );
        }

        Game game = new Game();
        game.setId(id);

        game.setName(gameDTO.getName());
        game.setDescription(gameDTO.getDescription());
        game.setPrice(gameDTO.getPrice());
        game.setThumbnailCover(gameDTO.getThumbnailCover());
        game.setPublishedAt(gameDTO.getPublishedAt());
        game.setPublisher(gameDTO.getPublisher());
        game.setCategories(gameDTO.getCategories());
        game.setPlatforms(gameDTO.getPlatforms());
        game.setCountries(gameDTO.getCountries());

        return gameRepository.saveAndFlush(game);
    }

    public GameDTO getDTOById(Long id) {
        Game game = getObjectById(id);
        GameDTO dto = new GameDTO();

        dto.setName(game.getName());
        dto.setDescription(game.getDescription());
        dto.setPrice(game.getPrice());
        dto.setThumbnailCover(game.getThumbnailCover());
        dto.setPublishedAt(game.getPublishedAt());
        dto.setPublisher(game.getPublisher());
        dto.setCategories(game.getCategories());
        dto.setPlatforms(game.getPlatforms());
        dto.setCountries(game.getCountries());

        return dto;
    }
}
