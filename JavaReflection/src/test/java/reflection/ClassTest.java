package reflection;

import org.junit.jupiter.api.Test;
import reflection.data.Person;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassTest {

    @Test
    void test(){
        Person person1 = new Person();

        Class<?> person2 = person1.getClass();

        Field[] fields = person2.getDeclaredFields();

        //Buat field
        System.out.println("Fields");
        for (var i : fields){
            System.out.println(i.getName() + " : " + i.getType().getName());
        }

        //Buat superclass
        System.out.println("Super Class");
        System.out.println(person2.getSuperclass());

        //Buat nama
        System.out.println("Nama");
        System.out.println(person2.getName());

        //Buat method
        System.out.println("Method");
        for (Method declaredMethod : person2.getDeclaredMethods()) {
            System.out.println(declaredMethod.getName());
        }
    }

    @Test
    void ambilField() throws IllegalAccessException {
        Person person = new Person("Ahmad", "Solikhin");

        Class<?> person1 = person.getClass();

        for (var i : person1.getDeclaredFields()){
            i.setAccessible(true);
            var result = i.get(person);
            System.out.println(result);
        }

        //mengubah data
        var obj = person1.getDeclaredFields()[0];
        obj.setAccessible(true);
        obj.set(person, "Gayuh");
        System.out.println(person.getFirstName());
    }

}
