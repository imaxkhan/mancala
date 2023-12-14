package com.bol.mancala.base.util;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@NotNull
@Pattern(regexp = ".*\\S.*")
@ReportAsSingleViolation
@Target({METHOD, FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
public @interface NotBlankOrNull {
    String message() default "Field must not be blank or null";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
