package minh.lehong.yourwindowyoursoul.service;

import io.jsonwebtoken.Claims;
import minh.lehong.yourwindowyoursoul.model.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.function.Function;

public interface JwtService {

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
    String extractUsername(String token);

    String generateToken(User user);

    boolean isTokenValid(String token, UserDetails userDetails);

    String invalidToken(String tokenHeader);
}
