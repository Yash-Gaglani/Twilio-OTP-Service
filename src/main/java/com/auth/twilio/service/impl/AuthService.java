package com.auth.twilio.service.impl;

import com.auth.common.exception.AuthException;
import com.auth.common.exception.BadRequestException;
import com.auth.common.exception.ExceptionCode;
import com.auth.common.exception.InvalidRequestException;
import com.auth.common.validator.ValidationResult;
import com.auth.common.validator.VerificationRequestValidator;
import com.auth.twilio.model.VerificationChannel;
import com.auth.twilio.model.VerificationRequest;
import com.auth.twilio.model.VerificationResponse;
import com.auth.twilio.service.TwilioService;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public static final String APPROVED = "approved";
    public static final String PENDING = "pending";

    @Autowired
    TwilioService twilioService;


    @Value("${test-accounts}")
    List<String> testAccounts;


    /*
     * This method is uo be used to send verification code for auth
     */
    public VerificationResponse sendSmsVerificationCode(VerificationRequest verificationRequest)
        throws AuthException {
        VerificationResponse verificationResponse = new VerificationResponse();
        try {

            // validate request
            ValidationResult validationResult = new VerificationRequestValidator().validate(
                verificationRequest);
            if (validationResult.notValid()) {
                throw new InvalidRequestException(validationResult.getExceptionCode(),
                    validationResult.getErrorMsg());
            }
            String prime = getVerificationPrime(verificationRequest);

            // bypass test accounts
            if (testAccounts.contains(prime)) {
                verificationResponse.setVerificationCodeSent(true);
                verificationResponse.setMessage("This is a test account please enter any 6 digit");
                return verificationResponse;
            }

            // send code
            String verificationStatus = twilioService.sendVerificationCode(prime,
                verificationRequest.getChannel());
            if (PENDING.equalsIgnoreCase(verificationStatus)) {
                verificationResponse.setVerificationCodeSent(true);
                verificationResponse.setMessage("Your verification message has been sent.");
            } else {
                throw new AuthException(ExceptionCode.ERROR, "Something went wrong!");
            }
        } catch (AuthException e) {
            throw e;
        } catch (Exception e) {
            throw new AuthException(ExceptionCode.ERROR, e.getMessage());
        }
        return verificationResponse;
    }


    public VerificationResponse checkVerificationCode(VerificationRequest verificationRequest)
        throws AuthException {
        try {

            // validate request
            ValidationResult validationResult = new VerificationRequestValidator().validate(
                verificationRequest);
            if (validationResult.notValid()) {
                throw new InvalidRequestException(validationResult.getExceptionCode(),
                    validationResult.getErrorMsg());
            }

            String prime = getVerificationPrime(verificationRequest);

            // verify code
            String verificationCode = verificationRequest.getVerificationCode();
            String status = testAccounts.contains(prime) ? APPROVED
                : twilioService.checkVerificationCode(prime, verificationCode);

            // verification success
            if (status.equalsIgnoreCase(APPROVED)) {
                return prepareVerificationResponse(verificationRequest, prime);
            }

            // verification failure
            else {
                throw new BadRequestException(
                    ExceptionCode.VERIFICATION_FAILED_INVALID_CODE, "Wrong OTP!");
            }
        } catch (AuthException e) {
            throw e;
        } catch (Exception e) {
            throw new AuthException(ExceptionCode.ERROR, e.getMessage());
        }
    }

    private String getVerificationPrime(VerificationRequest verificationRequest) {
        String prime = null;
        if (VerificationChannel.sms.equals(verificationRequest.getChannel())) {
            prime = verificationRequest.getMobileNumber();
        } else if (VerificationChannel.email.equals(verificationRequest.getChannel())) {
            prime = verificationRequest.getEmailId();
        }
        return prime;
    }


    @NotNull
    private VerificationResponse prepareVerificationResponse(
        VerificationRequest verificationRequest, String prime) {
        VerificationResponse verificationResponse = new VerificationResponse();
        verificationResponse.setMessage("Code Approved");
        verificationResponse.setVerificationCodeApproved(true);
         return verificationResponse;
    }
}
