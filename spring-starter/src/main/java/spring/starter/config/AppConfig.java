package spring.starter.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import spring.starter.security.util.JwtTokenFactory;

import java.security.Key;

@Configuration
public class AppConfig {

    @Bean
    public Key key() {
        byte[] keyBytes = Decoders.BASE64.decode("lkqelwklkdalldsakakdsqw9wqe98ewq9pqwe989089821kjkwjqekl98kljaksdjklsdaj");
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Bean
    public JwtTokenFactory jwtTokenFactory(Key key) {
        return new JwtTokenFactory(key);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }
}
