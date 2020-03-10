package moorcommon.annotation;


import moorcommon.annotation.impl.MoorEmailImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MoorEmailImpl.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MoorEmail {
    String message() default "Invalid E-mail address. ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
