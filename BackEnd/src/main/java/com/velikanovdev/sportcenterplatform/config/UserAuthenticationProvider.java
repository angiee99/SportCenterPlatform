package com.velikanovdev.sportcenterplatform.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.velikanovdev.sportcenterplatform.dto.UserDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;

@Component
public class UserAuthenticationProvider {

    private static final long TOKEN_VALIDITY = 3600000; // 1 hour in milliseconds

    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;
    private Algorithm algorithm;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey);
    }

    public String createToken(UserDTO user) {
        Date now = new Date();
        return JWT.create()
                .withSubject(user.username())
                .withIssuedAt(now)
                .withExpiresAt(new Date(now.getTime() + TOKEN_VALIDITY))
                .withClaim("username", user.username())
                .sign(algorithm);
    }

    public Authentication validateToken(String token) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decoded = verifier.verify(token);
        UserDTO user = UserDTO.builder().username(decoded.getSubject()).build();
        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }
}
