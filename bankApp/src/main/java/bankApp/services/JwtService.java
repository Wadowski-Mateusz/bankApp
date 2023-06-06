package bankApp.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class JwtService {

    private static final String KEY = "70337336763979244226452948404D635166546A576E5A7134743777217A25432A462D4A614E645267556B58703273357538782F413F4428472B4B6250655368";
//    private static final long KEY_LIFESPAN = 1000L * 60L * 15L;
    private static final long KEY_LIFESPAN = 1000L * 60L * 60L * 24L;
    public String extractLogin(String token) {
        return  extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails securityUserDetails) {
        final String login = extractLogin(token);
        return login.equals(securityUserDetails.getUsername()) && !isTokenExpired(token);

    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String createToken(UserDetails securityUserDetails) {
        return createToken(new HashMap<>(), securityUserDetails);
    }

    public String createToken(
            Map<String, Object> extraClaims,
            UserDetails securityUserDetails//from spring security
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(securityUserDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + KEY_LIFESPAN))
                .signWith(getSignInKey(), SignatureAlgorithm.HS512)
//                .signWith(getSignInKey(), SignatureAlgorithm.HS256) // TODO if not working, uncomment
                .compact();
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }




}
