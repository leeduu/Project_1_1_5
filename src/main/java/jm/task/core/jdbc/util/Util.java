package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {                     // реализуйте настройку соеденения с БД jdbc:mysql://localhost:3306/mysql

    public static Connection getMySQLConnection() throws SQLException, ClassNotFoundException {
        String hostName = "localhost";
        String dbName = "mysql";
        String userName = "root";
        String password = "Naushniki2007";

        return getMySQLConnection(hostName, dbName, userName, password);
    }
    public static Connection getMySQLConnection(String hostName, String dbName, String userName, String password)
            throws SQLException {
        //Class.forName("com.mysql.jdbc.Driver");
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
        Connection conn = DriverManager.getConnection(connectionURL, userName, password);
        return conn;
    }
}
