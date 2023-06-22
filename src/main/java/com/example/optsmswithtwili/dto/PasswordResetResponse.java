package com.example.optsmswithtwili.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PasswordResetResponse {

    private String message;
    private  OtpStatus status;
}
