package fr.poec.springboot.validator.annotation;

import fr.poec.springboot.validator.CountryValidator;
import fr.poec.springboot.validator.GameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = GameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExistingGame {

    String message() default "This game doesn't exists !";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}