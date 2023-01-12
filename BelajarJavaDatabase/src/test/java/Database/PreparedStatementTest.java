package Database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementTest {

    @Test
    void testPreparedStatement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();

        String username = "admin";
        String password = "admin";

        String sql = """
                SELECT * FROM admin WHERE username = ? AND password = ?
                """;

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);

        ResultSet result = statement.executeQuery();

        System.out.println((result.next()
                ? "Sukses Login Sebagai " + result.getString("username")
                : "Gagal Login"));


        statement.close();
        connection.close();
    }
}
