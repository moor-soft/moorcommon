package moorcommon.annotation;

import moorcommon.annotation.impl.MoorPasswordImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MoorPasswordImpl.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MoorPassword {
    String message() default "Minimum eight characters, at least one uppercase letter, one lowercase letter and one number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
