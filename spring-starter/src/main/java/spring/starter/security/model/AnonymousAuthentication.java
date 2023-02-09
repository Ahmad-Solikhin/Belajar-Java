package spring.starter.security.model;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

//Class buat nyimpen data kalo gagal authenticationnya
public class AnonymousAuthentication extends AbstractAuthenticationToken {
    public AnonymousAuthentication() {
        super(null);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return false;
    }
}
