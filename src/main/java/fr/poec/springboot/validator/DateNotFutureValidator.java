package fr.poec.springboot.validator;

import fr.poec.springboot.repository.CountryRepository;
import fr.poec.springboot.validator.annotation.DateNotFuture;
import fr.poec.springboot.validator.annotation.ExistingCountry;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@AllArgsConstructor
public class DateNotFutureValidator implements ConstraintValidator<DateNotFuture, Date> {

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
        return date.before(new Date());
    }
}
