package pw.karczewski.tasks.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = DictionaryValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DictionaryConstraint {
    String value();

    String message() default "This value doesn't exist in the dictionary";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
