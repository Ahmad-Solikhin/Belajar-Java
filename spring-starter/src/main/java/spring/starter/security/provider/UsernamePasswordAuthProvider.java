package spring.starter.security.provider;

//Interface dari spring security untuk menjadikan class security provider
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import spring.starter.service.AppUserService;

@Component
@AllArgsConstructor
public class UsernamePasswordAuthProvider implements AuthenticationProvider {

    /**
     * Method support untuk mengetahui apakah object authenticationnya support dengan authenticatiojn provider nya
     * Jadi object authenticationnya pada method authenticate bisa dianalogikan sebagai kuncinya dan AuthenticationProvider adalah mekanisme membukanya
     * Jadi gembok yang ada hanya bisa dibuka dengan kunci2x tertentu saja
     * */

    private final AppUserService appUserService;

    private final PasswordEncoder passwordEncoder;

    //Digunakan untuk melakukan proses authentication seperti authentication manager
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();

        UserDetails userDetails = appUserService.loadUserByUsername(username);

        if (!passwordEncoder.matches(password, userDetails.getPassword())) throw new BadCredentialsException("Invalid Username or Password");

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    //Method untuk menentukan apakah token yang diberikan didukung atau tidak
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
