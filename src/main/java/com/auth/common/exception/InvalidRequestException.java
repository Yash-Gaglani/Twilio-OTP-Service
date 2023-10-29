package com.auth.common.exception;

public class InvalidRequestException extends BadRequestException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    public InvalidRequestException(ExceptionCode code, String reason) {
        super(code, reason);
    }

}
