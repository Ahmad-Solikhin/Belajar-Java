package json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.validation.ObjectError;

import java.util.List;

public class FeatureTest {

    @Test
    void mapperFeature() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

        String json = """
                {"ID" : "1", "Name" : "Gayuh"}
                """;

        Person person = objectMapper.readValue(json, Person.class);
        Assertions.assertEquals("1", person.getId());
        System.out.println(person);
    }

    @Test
    void deserializationConfigure() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

        String json = """
                {"ID" : "1", "Name" : "Gayuh", "age" : 21, "hobbies" : "Tidur"}
                """;

        Person person = objectMapper.readValue(json, Person.class);

        Assertions.assertEquals(List.of("Tidur"), person.getHobbies());
        System.out.println(person);
    }

    @Test
    void serializationFeautre() throws JsonProcessingException {
        Person person = new Person();
        person.setId("1");
        person.setName("Gayuh");
        person.setHobbies(List.of("Tidur", "Rebahan"));

        Address address = new Address();
        address.setCity("Bekasi");
        address.setCountry("Indonesia");
        address.setStreet("Villa");

        person.setAddress(address);

        ObjectMapper objectMapper = new ObjectMapper()
                .configure(SerializationFeature.INDENT_OUTPUT, true);

        String json = objectMapper.writeValueAsString(person);
        System.out.println(json);

    }

    @Test
    void serializationInclusionTest() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);

        Person person = new Person();
        person.setId("1");
        person.setName("Gayuh");

        String json = objectMapper.writeValueAsString(person);
        System.out.println(json);
    }
}
