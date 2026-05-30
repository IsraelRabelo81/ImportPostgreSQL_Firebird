package database;


import java.sql.Connection;
import java.sql.DriverManager;


public class PostgresConnection {


   public static Connection getConnection(
           String host,
           String port,
           String dataBase,
           String user,
           String password
   ) throws Exception {
       String url = String.format(
               "jdbc:postgresql://%s:%s/%s",
               host, port, dataBase
       );
       return DriverManager.getConnection(url, user, password);
   }
}