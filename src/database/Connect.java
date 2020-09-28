package database;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {

    public static void connection(){
        String db = "jdbc:postgresql://localhost:5432/algoritmos";
        String user = "postgres";
        String password = "password";

        Connection connection;

        {
            try {
                connection = DriverManager.getConnection(db, user, password);
                System.out.print("Connection Stablished");
            } catch (Exception e) {
                System.out.print("Connection error" + e);
            }
        }
    }

}
