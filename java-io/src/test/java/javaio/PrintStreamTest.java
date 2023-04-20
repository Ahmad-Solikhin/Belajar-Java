package javaio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class PrintStreamTest {

    @Test
    void test(){
        Path path = Path.of("print.txt");
        try (OutputStream outputStream = Files.newOutputStream(path);
             PrintStream stream = new PrintStream(outputStream)){

            stream.println("Hallo Dunia");
            stream.println("Hallo Dunia");
            stream.println("Hallo Dunia");

        }catch (IOException e){
            Assertions.fail(e);
        }
    }
}
