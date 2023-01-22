package I18N;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SimpleDateFormatTest {

    @Test
    void simpleDateFormatTest() {
        String pattern = "EEEE dd MMMM yyyy";
        DateFormat dateFormat = new SimpleDateFormat(pattern);

        //format : String -> date
        String format = dateFormat.format(new Date());
        System.out.println(format);

        //Parse : date -> string
        try {
            Date date = dateFormat.parse(format);
            System.out.println(date);
        }catch (ParseException e){
            throw new RuntimeException(e);
        }
    }

    @Test
    void simpleDateFormatIndonesiaTest() {

        Locale indonesia = new Locale("id", "ID");

        String pattern = "EEEE dd MMMM yyyy";
        DateFormat dateFormat = new SimpleDateFormat(pattern, indonesia);

        //format : String -> date
        String format = dateFormat.format(new Date());
        System.out.println(format);

        //Parse : date -> string
        try {
            Date date = dateFormat.parse(format);
            System.out.println(date);
        }catch (ParseException e){
            throw new RuntimeException(e);
        }
    }
}
