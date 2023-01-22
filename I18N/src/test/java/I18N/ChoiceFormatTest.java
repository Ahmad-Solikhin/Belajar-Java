package I18N;

import org.junit.jupiter.api.Test;

import java.text.ChoiceFormat;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class ChoiceFormatTest {

    @Test
    void testChoiceFormat(){
        String pattern = "-1#negatif | 0#kosong | 1#satu | 1<banyak";
        ChoiceFormat choiceFormat = new ChoiceFormat(pattern);

        for (int i = -5; i < 3; i++) {
            System.out.println(choiceFormat.format(i));
        }
    }

    @Test
    void testChoiceFormatInMessageFormat(){
        Locale indonesia = new Locale("id", "ID");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("message", indonesia);

        String pattern = resourceBundle.getString("balance");

        MessageFormat messageFormat = new MessageFormat(pattern, indonesia);

        System.out.println(messageFormat.format(new Object[]{1_000_000}));
    }
}
