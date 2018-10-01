package ar.edu.itba.paw.webapp.forms.customValidators;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@NotNull
@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = {ImageValidator.class})
public @interface Image {

    String message() default "{ar.edu.itba.paw.webapp.constraints.validImage}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
