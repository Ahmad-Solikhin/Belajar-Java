package Database;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class SqlInjectionTest {
    @Test
    void testSqlInjection() throws SQLException {
        var connection = ConnectionUtil.getDataSource().getConnection();
        var statement = connection.createStatement();

        var username = "admin";
        var password = "admin";

        var sql = "SELECT * FROM admin WHERE username = '" + username + "' AND password = '" + password + "'";
        System.out.println(sql);
        var result = statement.executeQuery(sql);

        System.out.println((result.next()) ? "Sukses" : "Gagal");

        result.close();
        statement.close();
        connection.close();
    }
}
