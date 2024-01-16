package fr.poec.springboot.instant_faking.validator.annotation;

import fr.poec.springboot.instant_faking.validator.UniqueEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueEmailValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface UniqueEmail {

    String example() default "TOTO"; // Champ complémentaire de la validation

    String message() default "This email already exists !";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
