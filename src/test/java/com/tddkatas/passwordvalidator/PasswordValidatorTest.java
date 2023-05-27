package com.tddkatas.passwordvalidator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PasswordValidatorTest {
    private static final String DIGITS_ERROR = "The password must contain at least 2 numbers";
    private static final String LENGTH_ERROR = "Password must be at least 8 characters";
    private PasswordValidator passwordValidator;
    private ValidationResult result;

    @BeforeEach
    public void setup() {
        passwordValidator = new PasswordValidator();
    }

    private void assertResult(final boolean shouldValid, final List<String> expectedMessages) {
        assertEquals(shouldValid, result.getIsValid());
        assertIterableEquals(expectedMessages, result.getMessage());
    }

    @Test
    public void shouldReturnTrueForValidPassword() {
        result = passwordValidator.validate("12345678");
        assertResult(true, null);

    }

    @Test
    public void shouldCheckPasswordLength() {
        result = passwordValidator.validate("1234567");
        final List<String> expectedMessages = List.of(LENGTH_ERROR);
        assertResult(false, expectedMessages);
    }

    @Test
    public void shouldCheckForTwoDigits() {
        result = passwordValidator.validate("asdfghjkk");
        final List<String> expectedMessages = List.of(DIGITS_ERROR);
        assertResult(false, expectedMessages);
    }

    @Test
    public void shouldReturnMultipleMesagges() {
        result = passwordValidator.validate("asdf3g");
        final List<String> expectedStrings = List.of(
                LENGTH_ERROR,
                DIGITS_ERROR
        );
        assertResult(false, expectedStrings);
    }
}
