package spring.starter.security.model;

//Class untuk menyimpan data token yang dikirimkan di header, dan juga berperan untuk melakukan encode atau decrypt tokennya

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;

import java.security.Key;

@AllArgsConstructor
public class RawAccessJwtToken implements Token{

    private String token;

    public Jws<Claims> parseClaims(Key signKey){
        return Jwts.parserBuilder().setSigningKey(signKey).build().parseClaimsJws(token);
    }

    @Override
    public String getToken() {
        return this.token;
    }
}
