package fr.poec.springboot.service;

import fr.poec.springboot.custom_response.CustomApiResponse;
import fr.poec.springboot.custom_response.ErrorCustomApiResponse;
import fr.poec.springboot.custom_response.GameListCustomApiResponse;
import fr.poec.springboot.custom_response.GameShowCustomApiResponse;
import fr.poec.springboot.entity.Game;
import fr.poec.springboot.repository.GameRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GameService {

    private GameRepository gameRepository;

    public CustomApiResponse findAll() {
        GameListCustomApiResponse apiResponse = new GameListCustomApiResponse();
        ErrorCustomApiResponse errorApiResponse = new ErrorCustomApiResponse();

        apiResponse.setEntity(Game.class.getSimpleName());
        errorApiResponse.setEntity(Game.class.getSimpleName());

        List<Game> games = gameRepository.findAll();
        if(games.isEmpty()) {
            errorApiResponse.setCode(HttpStatus.NO_CONTENT.value());
            errorApiResponse.setMessage("There is no games.");
            return errorApiResponse;
        }
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setObjects(new ArrayList<>(games));

        return apiResponse;
    }

    public CustomApiResponse show(String field) {
        GameShowCustomApiResponse apiResponse = new GameShowCustomApiResponse();
        ErrorCustomApiResponse errorApiResponse = new ErrorCustomApiResponse();

        apiResponse.setEntity(Game.class.getSimpleName());
        errorApiResponse.setEntity(Game.class.getSimpleName());

        Optional<Game> game;
        try {
            Long id = Long.parseLong(field);
            game = this.gameRepository.findById(id);
        } catch (NumberFormatException e) {
            game = this.gameRepository.findBySlug(field);
        }

        if(game.isPresent()) {
            apiResponse.setCode(HttpStatus.OK.value());
            apiResponse.setObject(game.get());

            return apiResponse;
        }

        errorApiResponse.setCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
        errorApiResponse.setMessage("This game doesn't exist.");

        return errorApiResponse;
    }
}
