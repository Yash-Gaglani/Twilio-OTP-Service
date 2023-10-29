package com.auth.common.exception;

import java.io.Serial;

public class BadRequestException extends AuthException {

    /**
     * The Constant serialVersionUID.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    public BadRequestException(ExceptionCode statusCode, String reason) {
        super(statusCode, reason);
    }

}
