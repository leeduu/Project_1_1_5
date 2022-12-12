package jm.task.core.jdbc.util;


import java.sql.*;

public class Util {

    public static Connection connection;
    public static Statement statement;

    public static Connection getConnection() {

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Naushniki2007");
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        return connection;
    }
}
