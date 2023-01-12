package Database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ResultSetTest {
    @Test
    void testResultTest() throws SQLException {
        var connection = ConnectionUtil.getDataSource().getConnection();
        var statement = connection.createStatement();

        var sql = """
                SELECT * FROM customer
                """;
        var result = statement.executeQuery(sql);

        while (result.next()){
            //Kalau kolomnya join bisa gunakan "namaTbale.namaColomn"
            var id = result.getString("customer.id");
            var name = result.getString("name");
            var email = result.getString("email");

            System.out.println(
                    String.join(", ", id, name, email)
            );
        }

        result.close();
        statement.close();
        connection.close();
    }
}
