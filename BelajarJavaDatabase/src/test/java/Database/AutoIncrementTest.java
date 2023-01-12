package Database;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class AutoIncrementTest {

    @Test
    void testAutoIncrement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, "asgr39@gmail.com");
        statement.setString(2, "Halo agan semua");
        statement.executeUpdate();

        //Buat ngambil last insert id nya
        ResultSet resultSet = statement.getGeneratedKeys();

        if (resultSet.next()){
            System.out.println("Id Comment : " + resultSet.getInt(1));
        }

        statement.close();
        connection.close();


    }
}
