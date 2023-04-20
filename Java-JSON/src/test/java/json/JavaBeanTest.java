package json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JavaBeanTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void createJsonFromObject() throws JsonProcessingException {
        Person person = new Person();
        person.setId("1");
        person.setName("Gayuh");
        person.setHobbies(List.of("Tidur", "Rebahan"));

        Address address = new Address();
        address.setCity("Bekasi");
        address.setCountry("Indonesia");
        address.setStreet("Villa");

        person.setAddress(address);

        String json = objectMapper.writeValueAsString(person);

        System.out.println(json);
    }

    @Test
    void readJson() throws JsonProcessingException {
        String json = """
                {"id":"1","name":"Gayuh","hobbies":["Tidur","Rebahan"],"address":{"street":"Villa","city":"Bekasi","country":"Indonesia"}}
                """;

        Person person = objectMapper.readValue(json, Person.class);

        Assertions.assertEquals("1", person.getId());
        Assertions.assertEquals("Indonesia", person.getAddress().getCountry());

        System.out.println(person);
    }
}
