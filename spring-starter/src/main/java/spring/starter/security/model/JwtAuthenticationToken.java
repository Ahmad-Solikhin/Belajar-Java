package spring.starter.security.model;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private RawAccessJwtToken rawAccessJwtToken;
    private UserDetails userDetails;

    public JwtAuthenticationToken(RawAccessJwtToken token){
        super(null);
        this.rawAccessJwtToken = token;
        super.setAuthenticated(false);
    }

    public JwtAuthenticationToken(UserDetails userDetails, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);

        this.eraseCredentials();
        this.userDetails = userDetails;

        //jika proses authenticationnya berhasil akan disimpan
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.rawAccessJwtToken;
    }

    @Override
    public Object getPrincipal() {
        return this.userDetails;
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.rawAccessJwtToken = null;
    }
}
