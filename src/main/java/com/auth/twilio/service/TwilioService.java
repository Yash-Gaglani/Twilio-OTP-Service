package com.auth.twilio.service;

import com.auth.twilio.model.VerificationChannel;

public interface TwilioService {

    public String sendVerificationCode(String to, VerificationChannel channel);

    public String checkVerificationCode(String to, String code);

    public void sendSms(String mobileNumber, String message);

}
