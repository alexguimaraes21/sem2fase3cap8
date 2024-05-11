package br.com.fiap.atvcap8.services;

import br.com.fiap.atvcap8.models.UserModel;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${security.secret_word}")
    private String secretWord;

    @Value("${security.issuer}")
    private String issuer;

    public String tokenGenerator(UserModel user) throws JWTCreationException {
        Algorithm algorithm = this.getTokenAlgorithm(secretWord);
        return JWT
                .create()
                .withIssuer(this.issuer)
                .withSubject(user.getEmail())
                .withExpiresAt(this.getExpirationDate())
                .sign(algorithm);
    }

    public String tokenValidation(String token) throws JWTVerificationException {
        Algorithm algorithm = this.getTokenAlgorithm(secretWord);
        return JWT.require(algorithm)
                .withIssuer(issuer)
                .build()
                .verify(token)
                .getSubject();
    }

    public Instant getExpirationDate() { return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")); }

    public Algorithm getTokenAlgorithm(String secretWord) { return Algorithm.HMAC256(secretWord); }
}
