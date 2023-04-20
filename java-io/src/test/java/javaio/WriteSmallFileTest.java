package javaio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WriteSmallFileTest {

    @Test
    void test() throws IOException {
        Path path = Path.of("small1.txt");
        byte[] bytes = "Hello World".getBytes();
        Files.write(path, bytes);

        Path path1 = Path.of("small2.txt");
        Files.writeString(path1, "Hello World");
    }

    @Test
    void readSmallFile() throws IOException {
        Path path1 = Path.of("small1.txt");
        byte[] bytes = Files.readAllBytes(path1);
        String content1 = new String(bytes);
        Assertions.assertEquals("Hello World", content1);

        Path path2 = Path.of("small2.txt");
        String content2 = Files.readString(path2);
        Assertions.assertEquals("Hello World", content2);
    }
}
