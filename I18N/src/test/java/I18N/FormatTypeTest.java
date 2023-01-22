package I18N;

import org.junit.jupiter.api.Test;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class FormatTypeTest {

    @Test
    void testFormatType(){
        Locale indonesia = new Locale("id", "ID");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("message", indonesia);

        String pattern = resourceBundle.getString("status");

        MessageFormat messageFormat = new MessageFormat(pattern, indonesia);
        String format = messageFormat.format(new Object[]{
                "Gayuh", new Date(), 1_000_000
        });

        System.out.println(format);
    }
}
