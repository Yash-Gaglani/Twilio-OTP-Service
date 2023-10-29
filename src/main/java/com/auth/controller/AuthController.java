package com.auth.controller;

import com.auth.common.exception.ApiError;
import com.auth.common.exception.AuthException;
import com.auth.twilio.model.VerificationRequest;
import com.auth.twilio.model.VerificationResponse;
import com.auth.twilio.service.impl.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Auth", description = "Authentication")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/send-verification-code")
    @Operation(summary = "Send a verification code to user with channel sms/email.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "success", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = VerificationResponse.class))}),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)})
    public VerificationResponse sendVerificationCode(
        @RequestBody VerificationRequest verificationRequest) throws AuthException {
        return authService.sendSmsVerificationCode(verificationRequest);
    }

    @PostMapping("/check-verification-code")
    @Operation(summary = "Verify a user with verification code and channel sms/email.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "success", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = VerificationResponse.class))}),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)})
    public VerificationResponse checkVerificationCode(
        @RequestBody VerificationRequest verificationRequest) throws AuthException {
        return authService.checkVerificationCode(verificationRequest);
    }

}
