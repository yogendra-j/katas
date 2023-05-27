package com.tddkatas.passwordvalidator;

import java.util.ArrayList;
import java.util.List;

public class PasswordValidator {

    private static final String DIGITS_ERROR = "The password must contain at least 2 numbers";
    private static final String LENGTH_ERROR = "Password must be at least 8 characters";
    private static final String CAPITAL_LETTER_ERROR = "Password must contain at least one capital letter";

    public ValidationResult validate(String password) {
        List<String> errors = new ArrayList<>();
        if (password.length() < 8)
            errors.add(LENGTH_ERROR);
        if (digitCount(password) < 2)
            errors.add(DIGITS_ERROR);
        if (errors.size() > 0)
            return new ValidationResult(false, errors);
        return new ValidationResult(true, null);
    }

    private int digitCount(String password) {
        return (int)password.chars()
            .filter(Character::isDigit)
            .count();
    }

}
