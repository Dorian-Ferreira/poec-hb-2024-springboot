package fr.poec.springboot.instant_faking.validator.annotation;

import fr.poec.springboot.instant_faking.validator.PastDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PastDateValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PastDate {

    String message() default "The date has to be before the current date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
