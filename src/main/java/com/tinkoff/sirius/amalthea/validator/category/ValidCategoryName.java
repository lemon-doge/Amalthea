package com.tinkoff.sirius.amalthea.validator.category;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CategoryNameValidator.class)
public @interface ValidCategoryName {
    String message() default "Невалидное название категории";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
