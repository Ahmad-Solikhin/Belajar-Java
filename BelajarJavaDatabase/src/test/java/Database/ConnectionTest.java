package Database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {
    @BeforeAll
    static void beforeAll() {
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    void testConnection(){
        //Bisa tambahin timezone juga
        String jdbcUrl = "jdbc:mysql://localhost:3306/belajar_java_database?serverTimezone=Asia/Jakarta";
        String username = "root";
        String password = "root";


        //Try with resource
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            System.out.println("Berhasil Konek ke Database");
        }catch (SQLException e){
            Assertions.fail(e);
        }
    }
}
