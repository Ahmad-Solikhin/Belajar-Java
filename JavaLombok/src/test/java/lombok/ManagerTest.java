package lombok;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ManagerTest {

    @Test
    void test(){
        Employee manager1 = new Manager();
        manager1.setId("10");
        manager1.setName("Gayuh");

        Manager manager2 = new Manager();
        manager2.setId("10");
        manager2.setName("Gayuh");

        Assertions.assertEquals(manager2.getName(), manager1.getName());
        Assertions.assertEquals(manager1, manager2);

        System.out.println(manager2.getTotalEmployee());
    }
}
