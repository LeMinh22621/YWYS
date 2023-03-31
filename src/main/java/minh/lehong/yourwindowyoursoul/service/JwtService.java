package minh.lehong.yourwindowyoursoul.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JwtService {

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
    String extractUsername(String token);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);

    String invalidToken(String tokenHeader);
}
