package javaio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class ObjectInputStreamTest {

    @Test
    void test(){
        Path path= Path.of("gayuh.person");

        try (InputStream stream = Files.newInputStream(path);
             ObjectInputStream objectInputStream = new ObjectInputStream(stream)){

            Person person = (Person) objectInputStream.readObject();

            System.out.println(person.getId());
            System.out.println(person.getName());

        }catch (IOException | ClassNotFoundException e){
            Assertions.fail(e);
        }
    }
}
