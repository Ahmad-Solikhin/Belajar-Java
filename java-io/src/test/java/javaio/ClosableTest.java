package javaio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class ClosableTest {

    @Test
    void test(){
        Path path = Path.of("pom.xml");
        InputStream inputStream = null;

        try {
            inputStream = Files.newInputStream(path);
        }catch (IOException e){
            Assertions.fail(e);
        }finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Assertions.fail(e);
                }
            }
        }
    }

    @Test
    void tryWithResource(){
        Path path = Path.of("pom.xml");
        try (InputStream inputStream = Files.newInputStream(path)){
            //Do Something
        }catch (IOException e){
            Assertions.fail(e);
        }
    }
}
