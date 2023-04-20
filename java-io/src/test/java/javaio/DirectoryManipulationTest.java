package javaio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirectoryManipulationTest {

    @Test
    void test() throws IOException {
        Path path = Path.of("contoh");
        Files.createDirectory(path);
        Assertions.assertTrue(Files.isDirectory(path));

        Files.delete(path);
        Assertions.assertFalse(Files.exists(path));
    }
}
