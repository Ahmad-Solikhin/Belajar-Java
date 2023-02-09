package spring.starter.security.handler;

//Library yang digunakan untuk implements auth yang sukses
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import spring.starter.security.model.AccessJwtToken;
import spring.starter.security.util.JwtTokenFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class UsernamePasswordAuthSuccessHandler implements AuthenticationSuccessHandler {

    private ObjectMapper objectMapper;

    private final JwtTokenFactory jwtTokenFactory;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        AccessJwtToken accessJwtToken = jwtTokenFactory.createAccessJWTToken(userDetails.getUsername(), userDetails.getAuthorities());

        //Ini nanti jadi response json
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("token", accessJwtToken.getToken());
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getWriter(), resultMap);
    }
}
