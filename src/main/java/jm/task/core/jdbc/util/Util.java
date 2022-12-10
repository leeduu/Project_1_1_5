package jm.task.core.jdbc.util;


import java.sql.*;

public class Util {

    public static Connection connection = null;
    public static Statement statement = null;

    public static void getConnection() {

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Naushniki2007");
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
