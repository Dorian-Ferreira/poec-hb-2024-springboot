package fr.poec.springboot.service;

import fr.poec.springboot.DTO.UserOwnGameDTO;
import fr.poec.springboot.DTO.UserPostDTO;
import fr.poec.springboot.DTO.UserPutDTO;
import fr.poec.springboot.custom_response.CustomApiResponse;
import fr.poec.springboot.custom_response.ErrorCustomApiResponse;
import fr.poec.springboot.custom_response.UserListCustomApiResponse;
import fr.poec.springboot.custom_response.UserShowCustomApiResponse;
import fr.poec.springboot.entity.Game;
import fr.poec.springboot.entity.User;
import fr.poec.springboot.entity.UserOwnGame;
import fr.poec.springboot.exception.GameAlreadyBoughtException;
import fr.poec.springboot.exception.NotEnoughMoneyException;
import fr.poec.springboot.repository.UserOwnGameRepository;
import fr.poec.springboot.repository.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserOwnGameService {

    private UserOwnGameRepository userOwnGameRepository;
    private UserService userService;
    private GameService gameService;

    public CustomApiResponse persist(UserOwnGameDTO userOwnGameDTO) {
        UserShowCustomApiResponse apiResponse = new UserShowCustomApiResponse();
        apiResponse.setEntity(User.class.getSimpleName());

        ErrorCustomApiResponse errorApiResponse = new ErrorCustomApiResponse();
        errorApiResponse.setEntity(UserOwnGame.class.getSimpleName());

        UserOwnGame userOwnGame = null;
        try {
            userOwnGame = objectFromDto(userOwnGameDTO);
        } catch (GameAlreadyBoughtException e) {
            errorApiResponse.setCode(HttpStatus.FORBIDDEN.value());
            errorApiResponse.setMessage("Game already bought!");
            return errorApiResponse;
        } catch (NotEnoughMoneyException e) {
            errorApiResponse.setCode(HttpStatus.FORBIDDEN.value());
            errorApiResponse.setMessage("Not enough money in the wallet!");
            return errorApiResponse;
        }

        userOwnGame = userOwnGameRepository.saveAndFlush(userOwnGame);

        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setObject(userOwnGame.getUser());

        return apiResponse;
    }

    private UserOwnGame objectFromDto(UserOwnGameDTO userOwnGameDTO) throws GameAlreadyBoughtException, NotEnoughMoneyException {
        UserOwnGame userOwnGame = new UserOwnGame();

        User user = userService.getById(userOwnGameDTO.getUserId()).get();
        Game game = gameService.getById(userOwnGameDTO.getGameId()).get();

        if(userOwnGameRepository.existsByUserIdAndGameId(user.getId(), game.getId())) {
            throw new GameAlreadyBoughtException();
        }
        if(user.getWallet() < game.getPrice()) {
            throw new NotEnoughMoneyException();
        }

        user.setWallet(user.getWallet() - game.getPrice());

        userOwnGame.setUser(user);
        userOwnGame.setGame(game);

        userOwnGame.setGameTime(0);
        userOwnGame.setInstalled(false);

        return userOwnGame;
    }
}
