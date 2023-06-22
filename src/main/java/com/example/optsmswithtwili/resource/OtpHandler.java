package com.example.optsmswithtwili.resource;

import com.example.optsmswithtwili.dto.PasswordResetRequest;
import com.example.optsmswithtwili.service.OTPService;
import org.springframework.format.number.money.MonetaryAmountFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class OtpHandler {
    private OTPService otpService;

    public OtpHandler(OTPService otpService) {
        this.otpService = otpService;
    }
    public Mono<ServerResponse>sendOTP(ServerRequest serverRequest){
        return serverRequest.bodyToMono(PasswordResetRequest.class)
                .flatMap(dto-> otpService.sendPasswordResetOTP(dto))
                .flatMap(dto->ServerResponse.status(HttpStatus.OK).body(BodyInserters.fromValue(dto)));
    }
    public Mono<ServerResponse>validateOTP(ServerRequest serverRequest){

        return serverRequest.bodyToMono(PasswordResetRequest.class)
                .flatMap(dto-> otpService.validOTP(dto.getUserName(), dto.getOneTimePassword()))
                .flatMap(dto->ServerResponse.status(HttpStatus.OK).bodyValue(dto));
    }
}
