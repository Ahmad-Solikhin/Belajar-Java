package I18N;

import org.junit.jupiter.api.Test;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class CurrencyTest {

    @Test
    void testCurrency(){
        Locale indonesia = new Locale("id", "ID");

        Currency currency = Currency.getInstance(indonesia);

        System.out.println(currency.getDisplayName());
        System.out.println(currency.getCurrencyCode());
        System.out.println(currency.getSymbol(indonesia));
    }

    @Test
    void testCurrencyFormatNumber(){
        Locale indonesia = new Locale("id", "ID");

        Currency currency = Currency.getInstance(indonesia);
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(indonesia);

        String format = numberFormat.format(1_000_000.25);

        System.out.println(format);

        System.out.println(currency.getDisplayName());
        System.out.println(currency.getCurrencyCode());
        System.out.println(currency.getSymbol(indonesia));
    }

}
