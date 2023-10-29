package com.auth.common.validator;

import com.auth.common.exception.ExceptionCode;
import lombok.Value;

@Value
public class ValidationResult {

    boolean isValid;
    String errorMsg;
    ExceptionCode exceptionCode;

    public static ValidationResult valid() {
        return new ValidationResult(true, null, null);
    }

    public static ValidationResult invalid(ExceptionCode statusCode, String errorMsg) {
        return new ValidationResult(false, errorMsg, statusCode);
    }

    public boolean notValid() {
        return !isValid;
    }

}
