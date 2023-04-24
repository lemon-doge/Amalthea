package ru.hse.amaltheateam.wallets.validator.wallet;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class WalletNameValidator implements ConstraintValidator<ValidWalletName, String> {

    private static final Pattern walletNamePattern = Pattern.compile("^[а-яА-Яa-zA-Z\\d\\s]*");

    @Override
    public void initialize(ValidWalletName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null &&
                s.trim().length() > 0 && s.length() < 40 &&
                walletNamePattern.matcher(s).matches();
    }
}
