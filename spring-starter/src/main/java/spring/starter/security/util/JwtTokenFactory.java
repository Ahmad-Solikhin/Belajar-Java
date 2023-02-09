package spring.starter.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import spring.starter.security.model.AccessJwtToken;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

//Class untuk menghasilkan token JWT
@AllArgsConstructor
public class JwtTokenFactory {

    private final Key secret;

    public AccessJwtToken createAccessJWTToken(String username, Collection<? extends GrantedAuthority> authorities) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("scopes", authorities.stream().map(a -> a.getAuthority()).collect(Collectors.toList()));
        ZonedDateTime currentTime = ZonedDateTime.now(ZoneId.of("Asia/Jakarta"));
        ZonedDateTime expTime = currentTime.plusMinutes(20);

        Date currentTimeDate = Date.from(currentTime.toInstant());
        Date expiredTimeDate = Date.from(expTime.toInstant());

        String token = Jwts.builder().setClaims(claims)
                .setIssuer("http://subrutin.com").setIssuedAt(currentTimeDate)
                .setExpiration(expiredTimeDate)
                .signWith(secret, SignatureAlgorithm.HS256).compact();

        return new AccessJwtToken(token, claims);
    }

}
