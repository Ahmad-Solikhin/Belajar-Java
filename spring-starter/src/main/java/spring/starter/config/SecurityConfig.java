package spring.starter.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import spring.starter.security.filter.JwtAuthProcessingFilter;
import spring.starter.security.filter.UsernamePasswordAuthProcessingFilter;
import spring.starter.security.handler.UsernamePasswordAuthFailureHandler;
import spring.starter.security.handler.UsernamePasswordAuthSuccessHandler;
import spring.starter.security.provider.JwtAuthenticationProvider;
import spring.starter.security.provider.UsernamePasswordAuthProvider;
import spring.starter.security.util.JwtTokenFactory;
import spring.starter.security.util.SkipPathRequestMatcher;
import spring.starter.util.TokenExtractor;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Slf4j
public class SecurityConfig {
    private final static String AUTH_URL = "/api/v1/login";
    private final static String V1_URL = "/api/v1/**";
    private final static String V2_URL = "/api/v2/**";

    private final static List<String> PERMIT_ENDPOINT_LIST= List.of(AUTH_URL);
    private final static List<String> AUTHENTICATED_ENDPOINT_LIST= List.of(V1_URL, V2_URL);


    @Autowired
    private UsernamePasswordAuthProvider usernamePasswordAuthProvider;

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Bean
    public AuthenticationSuccessHandler successHandler(ObjectMapper objectMapper, JwtTokenFactory factory) {
        return new UsernamePasswordAuthSuccessHandler(objectMapper, factory);
    }

    @Bean
    public AuthenticationFailureHandler failureHandler(ObjectMapper objectMapper) {
        return new UsernamePasswordAuthFailureHandler(objectMapper);
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public UsernamePasswordAuthProcessingFilter usernamePasswordAuthProcessingFilter(ObjectMapper objectMapper,
                                                                                     AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler, AuthenticationManager authManager ) {
        UsernamePasswordAuthProcessingFilter filter = new UsernamePasswordAuthProcessingFilter(AUTH_URL, objectMapper, successHandler, failureHandler);
        filter.setAuthenticationManager(authManager);
        return filter;
    }

    @Bean
    public JwtAuthProcessingFilter jwtAuthProcessingFilter(TokenExtractor tokenExtractor, AuthenticationFailureHandler failureHandler, AuthenticationManager authManager ) {
        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(PERMIT_ENDPOINT_LIST, AUTHENTICATED_ENDPOINT_LIST);
        JwtAuthProcessingFilter filter = new JwtAuthProcessingFilter(matcher, tokenExtractor, failureHandler);
        filter.setAuthenticationManager(authManager);
        return filter;
    }

    @Autowired
    void registerProvider(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(usernamePasswordAuthProvider)
                .authenticationProvider(jwtAuthenticationProvider);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, UsernamePasswordAuthProcessingFilter usernamePasswordAuthProcessingFilter,
                                                   JwtAuthProcessingFilter jwtAuthProcessingFilter) throws Exception {
        http.authorizeHttpRequests().requestMatchers(V1_URL, V2_URL).authenticated()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic();

        http.addFilterBefore(usernamePasswordAuthProcessingFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthProcessingFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
