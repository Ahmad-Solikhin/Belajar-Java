package lombok;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerTest {

    @Test
    void test(){
        Customer customer = new Customer();

        customer.setId("1");
        customer.setName("Gayuh");

        System.out.println("Customer dengan id : " + customer.getId() + ", bernama : " + customer.getName());

        Customer customer1 = new Customer("2", "Ahmad");

        Assertions.assertEquals("2", customer1.getId());

    }

    @Test
    void equals(){
        Customer customer1 = new Customer("1", "Gayuh");
        Customer customer2 = new Customer("1", "Uyah");

        System.out.println(customer1.equals(customer2));
        System.out.println(customer1.hashCode() == customer2.hashCode());
    }
}
