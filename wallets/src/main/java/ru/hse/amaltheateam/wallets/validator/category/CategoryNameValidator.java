package ru.hse.amaltheateam.wallets.validator.category;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class CategoryNameValidator implements ConstraintValidator<ValidCategoryName, String> {

    private static final Pattern categoryNamePattern = Pattern.compile("^[а-яА-Яa-zA-Z\\d\\s]*");

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null &&
                s.trim().length() > 0 && s.length() < 40 &&
                categoryNamePattern.matcher(s).matches();
    }
}
