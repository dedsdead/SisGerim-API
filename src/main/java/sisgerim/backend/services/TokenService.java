package sisgerim.backend.services;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import sisgerim.backend.domain.pessoa.corretor.Corretor;

@Service
public class TokenService {
    @Value("${api.security.token.privateKey}")
    private String privateKey;

    public String generateToken(Corretor corretor){
        try {
            Algorithm algorithm = Algorithm.HMAC256(privateKey);
            String token = JWT.create()
                .withIssuer("api-sisgerim")
                .withSubject(corretor.getEmail())
                .withExpiresAt(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")))
                .sign(algorithm);
            return token;
        } catch (JWTCreationException e) {
            // TODO: handle exception
            return null;
        }
    }
    public String validateToken(String token){
        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.HMAC256(privateKey);
            JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("api-sisgerim")
                .build();
            decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject();
        } catch (JWTVerificationException e) {
            // TODO: handle exception
            return null;
        }
    }
}
