package bankApp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;


/* *******************************************/
/*                                           */
/*     DO NOT TOUCH DEPRECATED FUNCTIONS     */
/*        OR EVERYTHING WILL BREAK           */
/*                                           */
/* ********************************************/
@Component
public class JwtProvider {
    private static final long TOKEN_LIFETIME_IN_MILLIS = 86_400_000; // 24h
    String KEY = "TEST";


    public String createToken(Authentication authentication) {
        String login = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + TOKEN_LIFETIME_IN_MILLIS);
        return Jwts.builder()
                .setSubject(login)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, KEY)
                .compact();
    }



//    public String getLoginFromJwt(String token) {
//        Claims claims = Jwts.parserBuilder()
//                .setSigningKey(KEY)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//        return claims.getSubject();
//    }
//    public boolean validate(String token) {
//        try {
//            Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token);
//            return true;
//        } catch (Exception e) {
//            throw new AuthenticationCredentialsNotFoundException("Incorrect or expired JWT");
//        }
//    }

    public String getLoginFromJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("Incorrect or expired JWT");
        }
    }
}
