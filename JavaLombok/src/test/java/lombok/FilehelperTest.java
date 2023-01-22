package lombok;

import org.junit.jupiter.api.Test;

public class FilehelperTest {

    @Test
    void testPom() throws Exception{
        String text = FileHelper.loadFile("pom.xml");
        System.out.println(text);
    }
}
