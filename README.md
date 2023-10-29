# Project Title: Twilio Verify Service Integration

## Overview

This project demonstrates the integration of Twilio's Verify Service to send and verify OTP (One-Time Password) codes for user authentication and verification. Twilio's Verify Service provides a secure and reliable way to ensure that users are who they claim to be, making it an essential component for applications that require user identity verification.

## Features

- **OTP Generation:** Generate unique OTP codes for user verification.
- **OTP Delivery:** Send OTP codes to users via SMS, email, or other communication channels.
- **User Verification:** Allow users to verify their identity by entering the received OTP code.
- **Customization:** Customize the OTP message and expiration time according to application requirements.
- **Security:** Twilio's Verify Service ensures the security and validity of OTP codes.

## Technologies Used

- **Twilio:** Leveraged Twilio's Verify Service for OTP generation and delivery.
- **YourTechnologyStack:** Describe any other technologies or frameworks used in your project.

## Getting Started

1. **Twilio Account Setup:** You'll need to sign up for a Twilio account and obtain your Twilio Account SID and Auth Token.
2. **Twilio Verify Service Configuration:** Set up a Verify Service within your Twilio account to enable OTP functionality.
3. **Integration:** Integrate the Twilio Verify Service into your application by making API calls to send and verify OTP codes.
4. **Customization:** Customize OTP message content and expiration times according to your application's requirements.


##Steps to configure

1. paste your twilio account sid in application.properties file under as.twilio.accountSid=
2. paste your twilio secret in application.properties under as.twilio.authToken=
3. paste your twilio verify service sid in applicaion.properties file as as.twilio.authServiceSid=
4. paste your twilio number as as.twilio.authNumber=

##Test Accounts

If in your application you want some users that does not require OTP enter their details in test-accounts in properties file like +919120420420
this will let them bypass OTP by entering any 6 digit number

