package ru.hse.amaltheateam.wallets.validator.category;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class CategoryIconColorValidator implements ConstraintValidator<ValidCategoryIconColor, String> {

    private static final Pattern iconColorPattern = Pattern.compile("^#[\\dA-Fa-f]{6}");

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return iconColorPattern.matcher(s.trim()).matches();
    }
}
