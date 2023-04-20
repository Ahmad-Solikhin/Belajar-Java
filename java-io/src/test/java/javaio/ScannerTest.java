package javaio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class ScannerTest {

    @Test
    void test(){
        Path path = Path.of("print.txt");
        try (InputStream stream = Files.newInputStream(path);
             Scanner scanner = new Scanner(stream)){

            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                System.out.println(line);
            }

        }catch (IOException e){
            Assertions.fail(e);
        }
    }
}
