package lombok;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTest {

    @Test
    void test(){
        Login user1 = Login.create("Gayuh", "rahasia");

        Assertions.assertEquals("Gayuh", user1.getUsername());
        Assertions.assertEquals("rahasia", user1.getPassword());

        Login user2 = Login.createEmpty();

        Assertions.assertNull(user2.getUsername());

        System.out.println(user1);
    }
}
