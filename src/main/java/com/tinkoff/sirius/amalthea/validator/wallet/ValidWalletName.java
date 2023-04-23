package com.tinkoff.sirius.amalthea.validator.wallet;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = WalletNameValidator.class)
public @interface ValidWalletName {
    String message() default "Invalid wallet name.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
