package javaio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class InputStreamTest {

    @Test
    void test(){
        Path path = Path.of("pom.xml");
        try (InputStream inputStream = Files.newInputStream(path)){
            StringBuilder builder = new StringBuilder();
            int data;
            while ((data = inputStream.read()) != -1){
                builder.append((char) data);
            }

            System.out.println(builder.toString());

        } catch (IOException e) {
            Assertions.fail(e);
        }
    }

    @Test
    void inputStreamBuffer(){
        Path path = Path.of("pom.xml");
        try (InputStream stream = Files.newInputStream(path)){
            StringBuilder builder = new StringBuilder();
            byte[] bytes = new byte[1024];
            int length;
            int counter = 0;
            while ((length = stream.read(bytes)) != -1){
                System.out.println(length);
                builder.append(new String(bytes, 0, length));
                counter++;
            }
            System.out.println(builder.toString());
            System.out.println(counter);
        }catch (IOException e){
            Assertions.fail(e);
        }
    }
}
