package com.auth.common.validator;

import com.auth.common.exception.ExceptionCode;
import com.auth.common.util.AuthUtil;
import com.auth.twilio.model.VerificationChannel;
import com.auth.twilio.model.VerificationRequest;

public class VerificationRequestValidator extends ValidationStep<VerificationRequest>{

    @Override
    public ValidationResult validate(VerificationRequest verificationRequest) {
        if (verificationRequest.getChannel() == null) {
            return ValidationResult.invalid(ExceptionCode.INVALID_VERIFICATION_CHANNEL,
                "Invalid Verification Channel!");
        }
        if (VerificationChannel.sms.equals(verificationRequest.getChannel())) {
            if (!AuthUtil.checkValidMobileNumber(verificationRequest.getMobileNumber())) {
                return ValidationResult.invalid(ExceptionCode.INVALID_MOBILE_NUMBER,
                    "Invalid mobile number!");
            }
        } else if (VerificationChannel.email.equals(verificationRequest.getChannel())) {
            if (!AuthUtil.checkValidEmailId(verificationRequest.getEmailId())) {
                return ValidationResult.invalid(ExceptionCode.INVALID_EMAIL_ID,
                    "Invalid email id!");
            }
        }
        return checkNext(verificationRequest);
    }
}
