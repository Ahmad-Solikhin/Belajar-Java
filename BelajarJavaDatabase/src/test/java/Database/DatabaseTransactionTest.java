package Database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseTransactionTest {
    @Test
    void testDatabaseTransaction() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();

        //Mematikan autoconnection(false)
        connection.setAutoCommit(false);

        String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";

        for (int i = 0; i < 100; i++) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "huwaa@gmail.com");
            statement.setString(2, "Aku ke-" + i);
            statement.executeUpdate();
            statement.close();
        }

        //Digunakan untuk roolback query
        //connection.rollback();

        //Digunakan untuk commit query
        connection.commit();
        connection.close();


    }
}
