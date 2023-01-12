package Database;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class DatabaseMetaDataTest {
    @Test
    void testDatabaseMetData() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        DatabaseMetaData metaData = connection.getMetaData();

        System.out.println("Nama Database : " + metaData.getDatabaseProductName());
        System.out.println("Versi Database : " + metaData.getDatabaseProductVersion());

        ResultSet resultSet = metaData.getTables("belajar_java_database", null, null, null);

        while (resultSet.next()){
            String tableName = resultSet.getString("TABLE_NAME");
            System.out.println("Tabel : " + tableName);
        }

        resultSet.close();
        connection.close();
    }

    @Test
    void testParameterMetData() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();

        String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();

        System.out.println(parameterMetaData.getParameterCount());
        //Ini bisa dipake buat tau tipe datanya, tapi di driver di mysql ga support
//        System.out.println(parameterMetaData.getParameterType(1));
//        System.out.println(parameterMetaData.getParameterType(2));

        preparedStatement.close();
        connection.close();
    }

    @Test
    void testResultSetMetData() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();

        Statement statement = connection.createStatement();

        String sql = "SELECT * FROM sample_date";

        ResultSet result = statement.executeQuery(sql);

        ResultSetMetaData resultSetMetaData = result.getMetaData();

        System.out.println("Jumlah Kolom : " + resultSetMetaData.getColumnCount());
        System.out.println();
        int x = 1;
        for (int i = 1; i <= resultSetMetaData.getColumnCount() ; i++) {
            System.out.println("Kolom ke-" + x);
            System.out.println("Name : " + resultSetMetaData.getColumnName(i));
            System.out.println("Type : " + resultSetMetaData.getColumnType(i));
            System.out.println("Type Name :" + resultSetMetaData.getColumnTypeName(i));
            System.out.println();
            x++;
        }

        statement.close();
        result.close();
        connection.close();
    }
}
