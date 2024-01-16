package fr.poec.springboot.instant_faking.service;

import fr.poec.springboot.instant_faking.DTO.CountryDTO;
import fr.poec.springboot.instant_faking.DTO.UserOwnGameDTO;
import fr.poec.springboot.instant_faking.entity.Country;
import fr.poec.springboot.instant_faking.entity.User;
import fr.poec.springboot.instant_faking.entity.UserOwnGame;
import fr.poec.springboot.instant_faking.exception.NotFoundInstantFakingException;
import fr.poec.springboot.instant_faking.repository.CountryRepository;
import fr.poec.springboot.instant_faking.repository.UserOwnGameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserOwnGameService implements DAOServiceInterface<UserOwnGame> {

    private UserOwnGameRepository userOwnGameRepository;

    public List<UserOwnGame> findAll() {
        return userOwnGameRepository.findAll();
    }

    @Override
    public UserOwnGame getObjectById(Long id) {
        Optional<UserOwnGame> optionalUserOwnGame = findById(id);
        if (optionalUserOwnGame.isEmpty()) {
            throw new NotFoundInstantFakingException("UserOwnGame", "id", id);
        }
        return optionalUserOwnGame.get();
    }

    public Optional<UserOwnGame> findById(Long id) {
        return userOwnGameRepository.findById(id);
    }

    public UserOwnGame persist(UserOwnGameDTO userOwnGameDTO, Long id) {
        if (id != null && userOwnGameRepository.findById(id).isEmpty()) {
            throw new NotFoundInstantFakingException(
                "UserOwnGame", "id", id
            );
        }

        UserOwnGame userOwnGame = new UserOwnGame();
        userOwnGame.setId(id);

        return userOwnGameRepository.saveAndFlush(userOwnGame);
    }

    public List<UserOwnGame> getLastSales() {
        return userOwnGameRepository.findTop5ByOrderByCreatedAtDesc();
    }
}
