package fr.poec.springboot.service;

import fr.poec.springboot.custom_response.ApiResponse;
import fr.poec.springboot.custom_response.GameListApiResponse;
import fr.poec.springboot.custom_response.GameShowApiResponse;
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

    public ApiResponse findAll() {
        GameListApiResponse apiResponse = new GameListApiResponse();

        apiResponse.setEntity(Game.class.getSimpleName());
        List<Game> games = gameRepository.findAll();
        if(games.isEmpty()){
            apiResponse.setCode(HttpStatus.NO_CONTENT.value());
            apiResponse.addObject("There is no games.");
        } else {
            apiResponse.setCode(HttpStatus.OK.value());
            apiResponse.setObjects(new ArrayList<>(games));
        }

        return apiResponse;
    }

    public ApiResponse show(String field) {
        GameShowApiResponse apiResponse = new GameShowApiResponse();

        apiResponse.setEntity(Game.class.getSimpleName());

        Optional<Game> game;
        try {
            Long id = Long.parseLong(field);
            game = this.gameRepository.findById(id);
        } catch (NumberFormatException e) {
            game = this.gameRepository.findBySlug(field);
        }

        if(game.isPresent()){
            apiResponse.setCode(HttpStatus.OK.value());
            apiResponse.setObjects(Collections.singletonList(game.get()));
        } else {
            apiResponse.setCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
            apiResponse.addObject("This game doesn't exist.");
        }

        return apiResponse;

    }
}
