package ru.hse.amaltheateam.wallets.validator.category;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CategoryIconColorValidator.class)
public @interface ValidCategoryIconColor {
    String message() default "Invalid category name.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
