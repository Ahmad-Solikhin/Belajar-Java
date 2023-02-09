package spring.starter.security.filter;

//Library buat intercept spring security
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import spring.starter.dto.LoginRequest;
import spring.starter.exception.BadRequestException;

import java.io.IOException;

public class UsernamePasswordAuthProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private final AuthenticationSuccessHandler successHandler;
    private final AuthenticationFailureHandler failureHandler;
    private final ObjectMapper objectMapper;

    public UsernamePasswordAuthProcessingFilter(String defaultFilterProcessesUrl, ObjectMapper objectMapper, AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler) {
        //Isinya adalah path dari request yang ingin di intercept
        super(defaultFilterProcessesUrl);

        //Ini constructor injection untuk ObjectMapper
        this.objectMapper = objectMapper;
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
    }

    //Method ini berguna untuk melakukan authentication dari class yang di intercept
    //Authentication adalah object yang mereturn token yang nantinya akan digunakan untuk proses authentication
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        LoginRequest loginRequest = objectMapper.readValue(request.getReader(), LoginRequest.class);
        if (loginRequest.getUsername().isBlank() || loginRequest.getPassword().isBlank())
            throw new BadRequestException("Username or Password is Blank");

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        //Tinggal return untuk proses authenticationnya
        return this.getAuthenticationManager().authenticate(token);
    }

    //method dimana jika berhasil apa yang selanjutnya akan dilakukan
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        super.successfulAuthentication(request, response, chain, authResult);
        this.successHandler.onAuthenticationSuccess(request, response, authResult);
    }


    //method dimana jika gagal apa yang selanjutnya akan dilakukan
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
//        super.unsuccessfulAuthentication(request, response, failed);
        this.failureHandler.onAuthenticationFailure(request, response, failed);
    }

}
