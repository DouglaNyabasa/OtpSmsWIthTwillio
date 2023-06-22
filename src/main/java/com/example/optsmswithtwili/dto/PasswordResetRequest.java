package com.example.optsmswithtwili.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetRequest {

    private String phoneNumber;
    private String userName;
    private String oneTimePassword;
}
