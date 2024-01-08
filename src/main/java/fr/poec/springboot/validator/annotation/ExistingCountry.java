package fr.poec.springboot.validator.annotation;

import fr.poec.springboot.validator.CountryValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CountryValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExistingCountry {

    String message() default "This country doesn't exists !";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}