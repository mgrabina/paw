package ar.edu.itba.paw.webapp.forms.customValidators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = {NullableImageValidator.class})
public @interface NullableImage {

    String message() default "{ar.edu.itba.paw.webapp.constraints.validImage}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
