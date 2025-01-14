package pt.sapiens.sapiensAPI.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pt.sapiens.sapiensAPI.entities.User;

@Service
public class JwtService {

    @Value("${jwt.secret.key}")
    private String key;

    public String generateToken(UserDetails userDetails) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(key);

            return JWT.create()
                    .withIssuer("sapiensApi")
                    .withSubject(userDetails.getUsername())
                    .sign(algorithm);
        }
        catch (JWTCreationException jwtCreationException) {
            throw new RuntimeException(jwtCreationException.getCause());
        }
    }

    public String getSubject(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(key);

            return JWT.require(algorithm)
                    .withIssuer("sapiensApi")
                    .build()
                    .verify(token)
                    .getSubject();
        }
        catch (JWTVerificationException jwtVerificationException) {
            return "";
        }
    }
}
