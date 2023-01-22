package I18N;

import org.junit.jupiter.api.Test;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class MessageFormattest {

    @Test
    void testMessageFormat(){
        String pattern = "Hi {0} {1}, Apa yang bisa {2} lakukan untuk anda?";
        MessageFormat messageFormat = new MessageFormat(pattern);
        messageFormat.applyPattern(pattern);

        String format = messageFormat.format(new Object[] {
                "Selamat Datang",
                "Gayuh",
                "Tong Fang"
        });

        System.out.println(format);
    }

    @Test
    void testMessageFormatResourceBundle(){
        Locale indonesia = new Locale("id", "ID");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("message", indonesia);
        String pattern = resourceBundle.getString("welcome.message");

        MessageFormat messageFormat = new MessageFormat(pattern);
        String format = messageFormat.format(new Object[]{
                "Gayuh",
                "Huwalahumba"
        });

        System.out.println(format);
    }
}
