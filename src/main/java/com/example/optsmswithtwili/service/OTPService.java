package com.example.optsmswithtwili.service;

import com.example.optsmswithtwili.config.TwilioConfig;
import com.example.optsmswithtwili.dto.OtpStatus;
import com.example.optsmswithtwili.dto.PasswordResetRequest;
import com.example.optsmswithtwili.dto.PasswordResetResponse;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OTPService {
    private TwilioConfig twilioConfig;

    public OTPService(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }
    Map<String,String >otpMap = new HashMap<>();

    public Mono<PasswordResetResponse> sendPasswordResetOTP(PasswordResetRequest passwordResetRequest){

        PasswordResetResponse passwordResetResponse = null;
        try {
            PhoneNumber to = new PhoneNumber(passwordResetRequest.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());
            String otp = generateOTP();
            String otpMessage ="Dear Customer,Your OTP is ##"+ otp +"##. Use The Passcode to Complete your Transaction";

            Message message = Message.creator(to,from,otpMessage).create();

            otpMap.put(passwordResetRequest.getUserName(),otp);
            passwordResetResponse = new PasswordResetResponse(otpMessage, OtpStatus.DELIVERED);
        }catch (Exception e){
            passwordResetResponse = new PasswordResetResponse(e.getMessage(), OtpStatus.FAILED);

        }
        return Mono.just(passwordResetResponse);
    }
    public Mono<String> validOTP(String userInputOTP,String username){
        if (userInputOTP.equals(otpMap.get(username))){
            return Mono.just("VALID OTP");
        }else {
            return Mono.error(new IllegalArgumentException("INVALID OTP, PLEASE RETRY") );
        }

    }
    private String generateOTP(){
        return new DecimalFormat("000000").format(new Random().nextInt(999999));
    }
}
