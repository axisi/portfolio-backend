package com.example.portfolio.security.jwt;

import com.example.portfolio.security.services.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;

import java.util.Date;

@Component
public class JwtUtils {
    public static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${portfolio.app.jwtSecret}")
    private String jwtSecret;

    @Value("${portfolio.app.jwtExpirationMs}")
    private int jwtExpirationMs;


    public String generateJwtToken (Authentication authentication){

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();

    }

    public String getUserFromJwtToken(String token){
        return  Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();

    }

    public boolean validateJwtToken(String authToken) {
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;

        }catch (SignatureException e){
            LOGGER.error("Invalid JWT signature: {}",e.getMessage());
        }catch (MalformedJwtException e){
            LOGGER.error("Invalid JWT token: {}", e.getMessage());
        }catch (ExpiredJwtException e){
            LOGGER.error("JWT token is expired: {}", e.getMessage());
        }catch (UnsupportedJwtException e){
            LOGGER.error("JWT token is unsupported: {}", e.getMessage());
        }catch (IllegalArgumentException e){
            LOGGER.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
