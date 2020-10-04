package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Esta clase es la encargada de hacer la conexion a la base de datos en PostgreSQL
 */
public class Connect {

    public static Connection connection;

    public static Connection getConnection(){
        String db = "jdbc:postgresql://localhost:5432/algoritmos";
        String user = "postgres";
        String password = "password";
        try{
            if (connection == null){
                connection = DriverManager.getConnection(db, user, password);
            }
            return connection;
        }catch (SQLException e) {
            throw new RuntimeException("Connection error", e);
        }
    }

    static class getClose extends Thread{
        @Override
        public void run() {
            try {
                Connection connection = Connect.getConnection();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
