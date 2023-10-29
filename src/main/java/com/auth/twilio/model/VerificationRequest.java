package com.auth.twilio.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class VerificationRequest {

    @Schema(example = "+918812334589")
    private String mobileNumber;
    private String emailId;
    private String verificationCode;
    @NotNull
    private VerificationChannel channel;
    private String accountId;
    public VerificationRequest() {
    }

    public VerificationRequest(String mobileNumber, String emailId, String verificationCode,
        VerificationChannel channel) {
        this.mobileNumber = mobileNumber;
        this.emailId = emailId;
        this.verificationCode = verificationCode;
        this.channel = channel;
    }
}

