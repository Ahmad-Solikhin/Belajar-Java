package json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ObjectMapperTest {

    @Test
    void objectMapperTest(){
        ObjectMapper objectMapper = new ObjectMapper();

        Assertions.assertNotNull(objectMapper);
    }
}
