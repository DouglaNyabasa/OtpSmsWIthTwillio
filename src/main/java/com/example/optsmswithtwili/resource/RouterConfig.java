package com.example.optsmswithtwili.resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {
    private OtpHandler otpHandler;

    public RouterConfig(OtpHandler otpHandler) {
        this.otpHandler = otpHandler;
    }

    public RouterFunction<ServerResponse> handleSMS(){
        return RouterFunctions.route()
                .POST("/router/sendOTP",otpHandler::sendOTP)
                .POST("/router/validateOTP",otpHandler::validateOTP)
                .build();
    }
}
