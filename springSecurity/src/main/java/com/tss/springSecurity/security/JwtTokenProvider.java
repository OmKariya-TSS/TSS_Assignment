package com.tss.springSecurity.security;



import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtTokenProvider {

    @Value("${app-jwt-secret}")
    private String jwtSecret;

    @Value("${app-jwt-expiration-milliseconds}")
    private long jwtExpirationDate;

    public String generateToken(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();

        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(auth -> auth.getAuthority())
                .toList();

        claims.put("roles", roles);

        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(key(), Jwts.SIG.HS256)
                .compact();
    }

    private SecretKey key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
        // returns Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }


    public boolean validateToken(String token){
        try{
            Jwts.parser()
                    .verifyWith(key())
                    .build()
                    .parseSignedClaims(token);
            return true;
        }catch(MalformedJwtException exception){
            throw new UserApiException(HttpStatus.BAD_REQUEST,"Invalid JWT Token");
        }catch(ExpiredJwtException exception){
            throw new UserApiException(HttpStatus.BAD_REQUEST,"Expired JWT Token");
        }catch(UnsupportedJwtException exception){
            throw new UserApiException(HttpStatus.BAD_REQUEST,"Unsupported JWT Token");
        }catch(IllegalArgumentException exception){
            throw new UserApiException(HttpStatus.BAD_REQUEST,"JWT claims string is empty");
        }catch(Exception exception){
            throw new UserApiException(HttpStatus.BAD_REQUEST,"Invalid Credentials");
        }
    }

    public String getUsername(String token){
        Claims claims = Jwts.parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        String username = claims.getSubject();
        return username;
    }


}
