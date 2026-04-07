package database;


import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresConnection {
    public static Connection connection (String host, String port, String dataBase, String user, String password)
        throws Exception {
        String url = "jdbc:postgresql://" + host + ":" + port + "/" + dataBase;
        return DriverManager.getConnection(url, user, password);
    }
}