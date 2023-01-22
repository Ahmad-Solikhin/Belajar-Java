package datetime;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateFormatterTest {

    @Test
    void parsing(){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");

        LocalDate localDate = LocalDate.parse("07 10 2022", formatter);

        System.out.println(localDate);
    }

    @Test
    void format(){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");

        LocalDate localDate = LocalDate.parse("07 10 2022", formatter);

        //Mengembalikan date sesui dengan format yang diberikan dalam bentuk string
        String date = localDate.format(formatter);

        System.out.println(date);
    }

    @Test
    void defaultFormatter(){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");

        LocalDate localDate = LocalDate.parse("07 10 2022", formatter);

        //Mengembalikan date sesui dengan format yang diberikan dalam bentuk string
        String date = localDate.format(DateTimeFormatter.ISO_DATE);

        System.out.println(date);
    }

    @Test
    void I18N(){
        Locale indonesia = new Locale("id", "ID");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE MMMM yyyy", indonesia);

        LocalDate localDate = LocalDate.of(2022, 10, 7);

        //Format berubah jadi bahasa indonesia
        String date = localDate.format(formatter);

        System.out.println(date);
    }
}
