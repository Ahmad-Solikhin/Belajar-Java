package javaio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileTest {

    @Test
    void createFile(){
        File file = new File("file.txt");

        Assertions.assertEquals("file.txt", file.getName());
        Assertions.assertFalse(file.exists());
    }

    @Test
    void createFileExist(){
        File file = new File("src/main/resources/application.properties");

        Assertions.assertEquals("application.properties", file.getName());
        Assertions.assertTrue(file.exists());
    }

    @Test
    void createPath(){
        Path path = Path.of("files.txt");

        Assertions.assertEquals("files.txt", path.toString());
        Assertions.assertFalse(path.isAbsolute());
    }

    @Test
    void usingFiles(){
        Path path = Path.of("pom.xml");

        Assertions.assertEquals("pom.xml", path.toString());
        Assertions.assertFalse(path.isAbsolute());
        Assertions.assertTrue(Files.exists(path));
    }
}
