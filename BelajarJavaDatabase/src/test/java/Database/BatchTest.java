package Database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchTest {

    @Test
    void testStatement() throws SQLException{
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String sql = """
                INSERT INTO comments(email, comment) VALUES ('ahmadsgr39@gmail.com', 'Holla')
                """;

        for (int i = 0; i < 1_000; i++) {
            statement.addBatch(sql);
        }

        statement.executeBatch();

        statement.close();
        connection.close();
    }

    @Test
    void testPrepareStatement() throws SQLException{
        Connection connection = ConnectionUtil.getDataSource().getConnection();

        String sql = """
                INSERT INTO comments(email, comment) VALUES (?, ?)
                """;

        PreparedStatement statement = connection.prepareStatement(sql);

        for (int i = 0; i < 1_000; i++) {
            statement.clearParameters();
            statement.setString(1, "ahmadsgr39@gmail.com");
            statement.setString(2, "Hallo Prepare");
            statement.addBatch();
        }

        statement.executeBatch();

        statement.close();
        connection.close();
    }
}
