package fr.poec.springboot.validator.annotation;

import fr.poec.springboot.validator.DateNotFutureValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DateNotFutureValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DateNotFuture {

    String message() default "The date should not be in the future !";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
