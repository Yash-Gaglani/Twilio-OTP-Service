package com.auth.common.exception;

import java.io.Serial;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
@Setter
public class AuthException extends Exception {

    private ExceptionCode appErrorCode;

    @Serial
    private static final long serialVersionUID = 1L;

    public AuthException(String message) {
        super(message);
        this.appErrorCode = ExceptionCode.UNKNOWN;
    }

    public AuthException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public AuthException(ExceptionCode appErrorCode, String message) {
        super(message);
        this.appErrorCode = appErrorCode;
    }

    public AuthException(ExceptionCode appErrorCode, String message, Throwable throwable) {
        super(message, throwable);
        this.appErrorCode = appErrorCode;
    }

}

