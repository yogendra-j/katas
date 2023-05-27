package com.tddkatas.passwordvalidator;

import java.util.List;

public class ValidationResult {
    private boolean isValid;
    private List<String> message;

    ValidationResult(boolean isValid, List<String> message) {
        this.isValid = isValid;
        this.message = message;
    }

    public boolean getIsValid() {
        return isValid;
    }

    public List<String> getMessage() {
        return message;
    }

}
