package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    static UserDao userDao = new UserDaoJDBCImpl();

    Util util = new Util();

    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(util.URL, util.USERNAME, util.PASSWORD);
            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users(" +
                    "user_name varchar(30) not null," +
                    "user_lastName varchar(30) not null," +
                    "user_age int not null," +
                    "user_id int PRIMARY KEY AUTO_INCREMENT)");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try {
            connection = DriverManager.getConnection(util.URL, util.USERNAME, util.PASSWORD);
            statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            connection = DriverManager.getConnection(util.URL, util.USERNAME, util.PASSWORD);
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO users(user_name, user_lastName, user_age) VALUES ('" + name +
                    "','" + lastName + "','" + age + "')");
            System.out.println("User c именем " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try {
            String query = "DELETE FROM users WHERE user_id = ?";
            connection = DriverManager.getConnection(util.URL, util.USERNAME, util.PASSWORD);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, (int) id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> userList;
        try {
            connection = DriverManager.getConnection(util.URL, util.USERNAME, util.PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM users");
            resultSet = preparedStatement.executeQuery();
            userList = new ArrayList<User>();
            while (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString(1));
                user.setLastName(resultSet.getString(2));
                user.setAge(resultSet.getByte(3));
                userList.add(user);
            }
            System.out.println(userList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public void cleanUsersTable() {
        try {
            statement.executeUpdate("DELETE FROM users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
