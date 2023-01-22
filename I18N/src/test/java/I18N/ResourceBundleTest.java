package I18N;

import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleTest {

    @Test
    void testResourceBundle(){

        ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

        System.out.println(resourceBundle.getString("hello"));
        System.out.println(resourceBundle.getString("goodbye"));

    }

    @Test
    void testResourceBundleMaksa(){
        //Cara maksa
        Locale indonesia = new Locale("id", "ID");

        ResourceBundle resourceBundle = ResourceBundle.getBundle("message", indonesia);

        System.out.println(resourceBundle.getString("hello"));
        System.out.println(resourceBundle.getString("goodbye"));

    }
}
