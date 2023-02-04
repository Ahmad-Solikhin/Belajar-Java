package spring.starter;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

@Slf4j
@ExtendWith(SpringExtension.class)
@ActiveProfiles("default")
@SpringBootTest
public class PasswordGenerateTest {

    @Autowired
    private PasswordEncoder encoder;

    @Test
    public void encrypt(){
        log.info("Password {}", encoder.encode("admin"));
        log.info("Secure Id {}", UUID.randomUUID().toString());
    }
}
