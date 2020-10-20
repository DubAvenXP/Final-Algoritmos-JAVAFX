package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Esta clase es la encargada de hacer la conexion a la base de datos en PostgreSQL
 */
public class Connect {

    public static Connection getConnection(){
        String url = "jdbc:postgresql://ec2-18-210-180-94.compute-1.amazonaws.com:5432/d4d4i0e4qtv9r8";
        String user = "cpcwzbjoruwbfj";
        String pass = "2666b4d58f8bba26ca46124e459e908300d3e781ad0536d1b3285e7cc505666d";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, pass);
            if (connection != null) System.out.println("Connection established\n ----------------------------------");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return connection;
    }

    public static void closeConnection(){
        try {
            getConnection().close();
            System.out.println("Connection to DB closed\n ----------------------------------");
        } catch (SQLException e) {
            System.out.println("Connection to the DB could not be closed" + e);
        }
    }

}
