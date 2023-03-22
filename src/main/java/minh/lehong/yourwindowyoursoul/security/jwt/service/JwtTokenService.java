//package minh.lehong.yourwindowyoursoul.security.jwt.service;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.util.ObjectUtils;
//
//import java.io.Serializable;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//@Component
//public class JwtTokenService implements Serializable {
//
//    public static final long JWT_TOKEN_VALIDITY = 60 * 60;
//
//    @Value("${jwt.secret_key}")
//    private String secretKey;
//
//    public String generateTokenLogin(final String email) {
//        final Map<String, Object> claims = new HashMap<>();
//        return doGenerateToken(claims, email);
//    }
//
//    private String doGenerateToken(final Map<String, Object> claims, final String subject) {
//
//        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
//                .signWith(SignatureAlgorithm.HS512, secretKey).compact();
//    }
//
//    public Boolean validateTokenLogin(String token) {
//        return (!ObjectUtils.isEmpty(token) && !isTokenExpired(token));
//    }
//
//    private boolean isTokenExpired(String token) {
//        boolean check = false;
//        try {
//            if (token.contains("Bearer")) {
//                token = token.replace("Bearer ", "");
//            }
//            final Date expiration = getExpirationDateFromToken(token);
//            return expiration.before(new Date());
//        } catch (final Exception e) {
//            check = true;
//        }
//        return check;
//    }
//
//    private Date getExpirationDateFromToken(String token) {
//        return getClaimFromToken(token, Claims::getExpiration);
//    }
//
//    public <T> T getClaimFromToken(final String token, final Function<Claims, T> claimsResolver) {
//        final Claims claims = getAllClaimsFromToken(token);
//        return claimsResolver.apply(claims);
//    }
//    private Claims getAllClaimsFromToken(final String token) {
//        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
//    }
//}
