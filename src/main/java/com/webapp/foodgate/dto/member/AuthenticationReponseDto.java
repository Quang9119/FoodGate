package com.webapp.foodgate.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationReponseDto {
    private String token;
    private String refreshToken;
}
