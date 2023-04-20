package json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class JsonObjectTest {

    @Test
    void createJson() throws JsonProcessingException {
        Map<String, Object> person = Map.of(
                "firstName", "Gayuh",
                "lastName", "Raharjo",
                "age", 21
        );

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(person);

        System.out.println(json);
    }

    @Test
    void readJson() throws JsonProcessingException {
        String json = """
                {"lastName":"Raharjo","age":21,"firstName":"Gayuh"}
                """;

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> person = objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {
        });

        Assertions.assertEquals("Gayuh", person.get("firstName"));
    }
}
