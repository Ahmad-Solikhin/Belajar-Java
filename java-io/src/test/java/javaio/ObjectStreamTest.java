package javaio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class ObjectStreamTest {

    @Test
    void test(){
        Person person = new Person();
        person.setId("1");
        person.setName("Gayuh");

        Path path = Path.of("gayuh.person");

        try (OutputStream stream = Files.newOutputStream(path);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(stream)){

            objectOutputStream.writeObject(person);
            objectOutputStream.flush();

        }catch (IOException e){
            Assertions.fail(e);
        }
    }
}
