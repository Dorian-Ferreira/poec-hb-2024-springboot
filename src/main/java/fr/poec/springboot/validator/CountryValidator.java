package fr.poec.springboot.validator;

import fr.poec.springboot.repository.CountryRepository;
import fr.poec.springboot.validator.annotation.ExistingCountry;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CountryValidator implements ConstraintValidator<ExistingCountry, Long> {
    private final CountryRepository countryRepository;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        return countryRepository.findById(id).isPresent();
    }
}
