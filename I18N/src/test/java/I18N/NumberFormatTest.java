package I18N;

import org.junit.jupiter.api.Test;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class NumberFormatTest {

    @Test
    void testNumberFormat(){
        Locale indonesia = new Locale("id", "ID" +
                "");

        NumberFormat numberFormat = NumberFormat.getInstance(indonesia);

        String format = numberFormat.format(10_000_000.255);

        System.out.println(format);
    }

    @Test
    void testNumberFormatParse(){
        Locale indonesia = new Locale("id", "ID");

        NumberFormat numberFormat = NumberFormat.getInstance(indonesia);

        String format = numberFormat.format(10_000_000.255);

        System.out.println(format);

        System.out.println();
        try{
            Double result = numberFormat.parse(format).doubleValue();
            System.out.println(result);
        }catch (ParseException e){
            System.out.println(e.getMessage());
        }
    }
}
