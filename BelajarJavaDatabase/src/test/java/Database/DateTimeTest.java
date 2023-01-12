package Database;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class DateTimeTest {

    @Test
    void testDateTime() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();

        String sql = "INSERT INTO sample_date(time, date, date_time) VALUES (?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setTime(1, new Time(System.currentTimeMillis()));
        statement.setDate(2, new Date(System.currentTimeMillis()));
        statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

        statement.executeUpdate();

        statement.close();
        connection.close();
    }

    @Test
    void testDateQuery() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();

        Statement statement = connection.createStatement();

        String sql = "SELECT * FROM sample_date";

        ResultSet result =  statement.executeQuery(sql);

        int x = 1;
        while (result.next()){
            System.out.println("Data Ke-" + x);
            Time time = result.getTime("time");
            System.out.println("TIme : " + time);

            Date date = result.getDate("date");
            System.out.println("Date : " + date);

            Timestamp timestamp = result.getTimestamp("date_time");
            System.out.println("Date Time : " + timestamp);
            System.out.println();
            x++;
        }

        result.close();
        statement.close();
        connection.close();
    }
}
