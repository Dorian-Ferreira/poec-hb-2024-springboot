package fr.poec.springboot.validator;

import fr.poec.springboot.repository.CountryRepository;
import fr.poec.springboot.repository.UserRepository;
import fr.poec.springboot.validator.annotation.ExistingCountry;
import fr.poec.springboot.validator.annotation.ExistingUser;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserValidator implements ConstraintValidator<ExistingUser, Long> {
    private final UserRepository userRepository;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        return userRepository.findById(id == null ? 0 : id).isPresent();
    }
}
