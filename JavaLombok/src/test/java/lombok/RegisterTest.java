package lombok;

import org.junit.jupiter.api.Test;

public class RegisterTest {

    @Test
    void test(){
        Register register1 = new Register("Gayuh", "rahasia");
        Register register2 = register1.withUsername("Ahmad");

        System.out.println(register1);
        System.out.println(register2);
    }
}
