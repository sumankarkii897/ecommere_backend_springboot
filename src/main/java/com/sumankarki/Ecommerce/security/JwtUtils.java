package com.sumankarki.Ecommerce.security;

import com.sumankarki.Ecommerce.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.function.Function;

@Service
@Slf4j

public class JwtUtils {

    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    private SecretKey secretKey;

    @Value("${secretJwtString}")
    private String secretJwtString;

    @PostConstruct
    private void init(){
        byte[] keyBytes = secretJwtString.getBytes();
        this.secretKey = new SecretKeySpec(keyBytes, "HmacSHA256");

    }

    public String generateToken(User user){
        return Jwts.builder()
                .subject(user.getEmail())
//                .claims("userId",user.getId().toString())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .signWith(secretKey)
                .compact();

    }

    public String getUsernameFromToken(String token){
        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims,T> claimsfunction){
return claimsfunction.apply(Jwts
        .parser()
        .verifyWith(secretKey)
        .build()
        .parseSignedClaims(token)
        .getPayload());

    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token){
        return extractClaims(token,Claims::getExpiration).before(new Date());
    }
}
