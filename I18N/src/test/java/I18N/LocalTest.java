package I18N;

import org.junit.jupiter.api.Test;

import java.util.Locale;

public class LocalTest {

    @Test
    void testLocale(){
        String language = "id";
        String country = "ID";

        Locale locale = new Locale(language, country);

        System.out.println(locale.getCountry());
        System.out.println(locale.getLanguage());

        System.out.println();

        System.out.println(locale.getDisplayCountry());
        System.out.println(locale.getDisplayLanguage());
    }
}
