package fr.poec.springboot.instant_faking.rest_controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.poec.springboot.instant_faking.entity.Game;
import fr.poec.springboot.instant_faking.json_views.JsonViews;
import fr.poec.springboot.instant_faking.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/game")
public class GameRestController {

    private GameService gameService;

    @GetMapping
    @JsonView(JsonViews.GameListView.class)
    public List<Game> list() {
        return gameService.findAll();
    }

    @GetMapping("/category/{slug}")
    @JsonView(JsonViews.GameListView.class)
    public List<Game> listByCategory(@PathVariable String slug) {
        return gameService.findByCategory(slug);
    }

    @GetMapping("/{slug}")
    @JsonView(JsonViews.GameShowView.class)
    public Game show(@PathVariable String slug) {
        return gameService.findBySlug(slug);
    }

}