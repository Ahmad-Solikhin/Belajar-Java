package util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseUtil {

    private static HikariDataSource hikariDataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setUsername("root");
        config.setPassword("root");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/belajar_java_todolist?serverTimezone=Asia/Jakarta");

        //Pool
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setIdleTimeout(60_000);
        config.setMaxLifetime(10 * 60 * 1_000); //Format milisecond

        hikariDataSource = new HikariDataSource(config);
    }

    public static HikariDataSource getDataSource(){
        return hikariDataSource;
    }

}
