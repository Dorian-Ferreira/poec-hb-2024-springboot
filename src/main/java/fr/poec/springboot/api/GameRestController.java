package fr.poec.springboot.api;

import com.fasterxml.jackson.annotation.JsonView;
import fr.poec.springboot.custom_response.ApiResponse;
import fr.poec.springboot.json_view.JsonViews;
import fr.poec.springboot.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/game")
public class GameRestController {

    private GameService gameService;

    @GetMapping
    @JsonView(JsonViews.GameListView.class)
    @Operation(summary = "Get a list of Game", description = "Returns all Game of the application")
    public ApiResponse list() {
        return gameService.findAll();
    }

    @GetMapping(path = "/{field}")
    @JsonView(JsonViews.GameShowView.class)
    @Operation(summary = "Get a Game by id or slug", description = "Returns a Game as per the id or slug")
    public ApiResponse show(@PathVariable String field) {
        return gameService.show(field);
    }
}