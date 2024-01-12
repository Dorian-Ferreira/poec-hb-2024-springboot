package fr.poec.springboot.validator;

import fr.poec.springboot.repository.CountryRepository;
import fr.poec.springboot.repository.GameRepository;
import fr.poec.springboot.validator.annotation.ExistingCountry;
import fr.poec.springboot.validator.annotation.ExistingGame;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GameValidator implements ConstraintValidator<ExistingGame, Long> {
    private final GameRepository gameRepository;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        return gameRepository.findById(id == null ? 0 : id).isPresent();
    }
}
