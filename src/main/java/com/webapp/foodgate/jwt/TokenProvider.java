package com.webapp.foodgate.jwt;

import com.webapp.foodgate.service.CustomUserDetail;
import com.webapp.foodgate.service.DomainUserDetailsService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class TokenProvider {
    private final Logger log = LoggerFactory.getLogger(TokenProvider.class);
    private static final String AUTHORITIES_KEY = "auth";
    private final DomainUserDetailsService userDetailsService;
    private Key key;
    private long tokenValidityInMilliseconds;
    private long tokenValidityInMillisecondsForRememberMe;
    private long refreshTokenValidityInMilliseconds;
    public TokenProvider(DomainUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostConstruct
    public void init() {
        byte[] keyBytes = null;
        String secret = "NjZiMzE1YjAwN2Y0MjI4ZmRiZDM4YTU2ZTEwMzU2NjYxYmQ5YjFiZjQzYjJmYjA0OTY0OTY0ZGEzYmFiNDJmMzhiMDA4NjAwN2I1OGZjODgzYzlkYmM4ZTFhZGQ4M2Y0ZDQ2Zjg1MjBjOGIyYWQxZGZjNzFhMDlmNzhmMTAzYTc=";
        if (!StringUtils.isEmpty(secret)) {
            log.warn("Warning: the JWT key used is not Base64-encoded. " +
                    "We recommend using the 'jhipster.security.authentication.jwt.base64-secret' key for optimum security.");
            keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        }
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.tokenValidityInMilliseconds = 1000 * 3600;
        this.tokenValidityInMillisecondsForRememberMe = 1000 * 7200;
        this.refreshTokenValidityInMilliseconds = 1000 * 3600 * 100;
    }

    public String createToken(Authentication authentication, boolean rememberMe) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        long now = (new Date()).getTime();
        Date validity;
        if (rememberMe) {
            validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
        } else {
            validity = new Date(now + this.tokenValidityInMilliseconds);
        }
        CustomUserDetail springSecurityUser = (CustomUserDetail) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .setId(springSecurityUser.getId())
                .compact();
    }

    public String createRefreshToken(Authentication authentication) {
        long now = (new Date()).getTime();
        Date validity = new Date(now + this.refreshTokenValidityInMilliseconds);
        CustomUserDetail springSecurityUser = (CustomUserDetail) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(authentication.getName())
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .setId(springSecurityUser.getId())
                .compact();
    }

    public String generateTokenFromRefreshToken(String refreshToken) {
        if (!validateToken(refreshToken))
            return null;
        String subject = Jwts.parser().setSigningKey(key).parseClaimsJws(refreshToken).getBody().getSubject();

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(subject);
//        status lock
        if (userDetails.getAuthorities().isEmpty())
            return null;

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        return createToken(authentication, true);
    }

    public String updateRefreshToken(String refreshToken) {
        if (!validateToken(refreshToken))
            return null;
        String subject = Jwts.parser().setSigningKey(key).parseClaimsJws(refreshToken).getBody().getSubject();

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(subject);
        //        status lock
        if (userDetails.getAuthorities().isEmpty())
            return null;

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        long now = (new Date()).getTime();
        Date validity = new Date(now + this.refreshTokenValidityInMilliseconds);
        CustomUserDetail springSecurityUser = (CustomUserDetail) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(authentication.getName())
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .setId(springSecurityUser.getId())
                .compact();
    }

    // Tra ve 1 Authentication tu chuoi token
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY)
                                .toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
//        Thong tin principal se duoc luu trong CustomUserDetail la 1 User trong security core
        CustomUserDetail principal = new CustomUserDetail(
                claims.getSubject(), "",
                authorities,
                claims.getId());
//        UsernamePasswordAuthenticationToken la 1 Authentication
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);

    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.info("Invalid JWT token.");
            log.trace("Invalid JWT token trace.", e);
        }
        return false;
    }
}
