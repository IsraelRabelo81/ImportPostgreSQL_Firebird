package database;


import java.sql.Connection;
import java.sql.DriverManager;

public class FirebirdConnection {

    public static Connection connection(String path, String user, String password) throws Exception {
        String url = "jdbc:firebirdsql://localhost:3050" + path;
        return DriverManager.getConnection(url, user, password);
    }
}