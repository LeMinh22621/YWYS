package minh.lehong.yourwindowyoursoul.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import minh.lehong.yourwindowyoursoul.model.entity.User;
import minh.lehong.yourwindowyoursoul.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {
    private String secretKey = "3F4428472B4B6150645367566B5970337336763979244226452948404D635165";//Base64.getEncoder().encodeToString("lehongminh".getBytes());

    @Value("${jwt.expiration_time}")
    private String expiredTime;

    @Override
    public String extractUsername(String token) throws ExpiredJwtException{
        return  extractClaim(token, Claims::getSubject);
    }

    @Override
    public String generateToken(User user) {
        return generateToken(new HashMap<>(), user);
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) throws ExpiredJwtException{
        String username = this.extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    @Override
    public String invalidToken(String tokenHeader) {
        String token = tokenHeader.replace("Bearer ", "");
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();

        // Set the expiration time of the JWT token to an earlier time than the current time
        claims.setExpiration(new Date(System.currentTimeMillis() - Long.parseLong(expiredTime)));

        // Generate a new JWT token with the updated claims
        String newToken = Jwts.builder().setClaims(claims).signWith(getSignInKey()).compact();

        return newToken;
    }

    private boolean isTokenExpired(String token) throws ExpiredJwtException{
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) throws ExpiredJwtException{
        return extractClaim(token, Claims::getExpiration);
    }

    private String generateToken(Map<String, Object> extraClaims, User user) {
        extraClaims.put("email", user.getEmail());
        extraClaims.put("role", user.getRole());
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(expiredTime)))
                .signWith(SignatureAlgorithm.HS256, getSignInKey())
                .compact();
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) throws ExpiredJwtException{
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) throws ExpiredJwtException
    {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
