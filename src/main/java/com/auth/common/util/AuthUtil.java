package com.auth.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthUtil {

    public static boolean checkValidMobileNumber(String mobileNumber) {
        Pattern pattern = Pattern.compile("^\\+91\\d{10}$");
        Matcher matcher = pattern.matcher(mobileNumber);
        return matcher.matches();
    }

    public static boolean checkValidEmailId(String emailId) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
        Matcher matcher = pattern.matcher(emailId);
        return matcher.matches();
    }

}
