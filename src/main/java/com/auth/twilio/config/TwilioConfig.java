package com.auth.twilio.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("as.twilio")
public class TwilioConfig {

    private String accountSid;
    private String authToken;
    private String authNumber;
    private String authServiceSid;
    private String chatApiKey;
    private String chatApiSecret;
}
