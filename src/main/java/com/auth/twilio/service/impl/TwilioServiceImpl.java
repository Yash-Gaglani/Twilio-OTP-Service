package com.auth.twilio.service.impl;

import com.auth.twilio.config.TwilioConfig;
import com.auth.twilio.model.VerificationChannel;
import com.auth.twilio.service.TwilioService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwilioServiceImpl implements TwilioService {

    @Autowired
    private TwilioConfig twilioConfig;

    @Override
    public String sendVerificationCode(String to, VerificationChannel channel) {
        Verification verification = Verification.creator(twilioConfig.getAuthServiceSid(),
            to, channel.toString()).create();
        return verification.getStatus();
    }

    @Override
    public String checkVerificationCode(String to, String verificationCode) {
        Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
        VerificationCheck verificationCheck = VerificationCheck.creator(
                twilioConfig.getAuthServiceSid()).setTo(to).setCode(verificationCode)
            .create();
        return verificationCheck.getStatus();
    }

    @Override
    public void sendSms(String mobileNumber, String message) {
        MessageCreator messageCreator = Message.creator(
            new PhoneNumber(mobileNumber),
            new PhoneNumber(twilioConfig.getAuthNumber()), message);
        messageCreator.create();
    }

    @PostConstruct
    public void init() {
        Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
    }

}
