package com.auth.twilio.model;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerificationResponse {

    private boolean isVerificationCodeSent;
    private boolean isVerificationCodeApproved;
    private String message;

    public VerificationResponse() {
    }

    public VerificationResponse(boolean isVerificationCodeSent, boolean isVerificationCodeApproved,
        String message) {
        this.isVerificationCodeSent = isVerificationCodeSent;
        this.isVerificationCodeApproved = isVerificationCodeApproved;
        this.message = message;
    }
}