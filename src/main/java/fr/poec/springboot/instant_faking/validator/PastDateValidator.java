package fr.poec.springboot.instant_faking.validator;

import fr.poec.springboot.instant_faking.validator.annotation.PastDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Date;

public class PastDateValidator implements ConstraintValidator<PastDate, Date> {

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
        return date.before(new Date());
    }

}
