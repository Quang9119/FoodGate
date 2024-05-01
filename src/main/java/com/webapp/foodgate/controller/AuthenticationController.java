package com.webapp.foodgate.controller;

import com.webapp.foodgate.dto.ApiResponse;
import com.webapp.foodgate.dto.member.AuthenticationReponseDto;
import com.webapp.foodgate.form.member.AuthenticateRequestForm;
import com.webapp.foodgate.form.member.RefreshTokenResponseForm;
import com.webapp.foodgate.jwt.TokenProvider;
import com.webapp.foodgate.service.DomainUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class AuthenticationController {
    @Autowired
    private final DomainUserDetailsService userDetailsService;
    @Autowired
    private final TokenProvider tokenProvider;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public AuthenticationController(DomainUserDetailsService userDetailsService, TokenProvider tokenProvider, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/authenticate")
    public ApiResponse<AuthenticationReponseDto> authenticateUser(@RequestBody AuthenticateRequestForm authenticateRequestForm) {
        ApiResponse<AuthenticationReponseDto> apiResponse = new ApiResponse<AuthenticationReponseDto>();
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticateRequestForm.getLogin());

        if (userDetails == null) {
            apiResponse.setResult(false);
            apiResponse.setData(new AuthenticationReponseDto(null, null));
            apiResponse.setMessage("login failed");
            apiResponse.setHttpCode(HttpStatus.NOT_FOUND.value());
            return apiResponse;
        }
        if (userDetails.getAuthorities().isEmpty()) {
            apiResponse.setResult(false);
            apiResponse.setData(new AuthenticationReponseDto(null, null));
            apiResponse.setMessage("User may be locked (not ACTIVE)");
            apiResponse.setHttpCode(HttpStatus.LOCKED.value());
            return apiResponse;
        }


        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        final String token = tokenProvider.createToken(authentication, true);
        final String refreshToken = tokenProvider.createRefreshToken(authentication);

        apiResponse.setResult(true);
        apiResponse.setData(new AuthenticationReponseDto(token, refreshToken));
        apiResponse.setMessage("login successed");
        apiResponse.setHttpCode(HttpStatus.OK.value());

        return apiResponse;
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenResponseForm refreshTokenResponse) {
        final String token = tokenProvider.generateTokenFromRefreshToken(refreshTokenResponse.getRefreshToken());
        final String refreshToken = tokenProvider.updateRefreshToken(refreshTokenResponse.getRefreshToken());
        if (token == null || refreshToken == null) {
            return ResponseEntity.status(HttpStatus.LOCKED)
                    .body(new ApiResponse<AuthenticationReponseDto>
                            (false, "LOCKED", HttpStatus.LOCKED.value(), new AuthenticationReponseDto(null, null), "User may be locked (not ACTIVE)"));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<AuthenticationReponseDto>(true, "SUCCESS", HttpStatus.OK.value(), new AuthenticationReponseDto(token, refreshToken), "refresh successed"));
    }
}
