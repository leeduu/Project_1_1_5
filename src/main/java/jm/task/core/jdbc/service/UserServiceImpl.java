package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    public void createUsersTable() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Util.statement.executeUpdate("CREATE TABLE IF NOT EXISTS USERS (" +
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
            Util.statement.executeUpdate("DROP TABLE USERS");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            Util.statement.executeUpdate("INSERT INTO USERS(user_name, user_lastName, user_age) VALUES ('" + name +
                    "','" + lastName + "','" + age + "')");
            System.out.println("User c именем " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try {
            String query = "DELETE FROM USERS WHERE user_id = ?";
            PreparedStatement preparedStatement = Util.connection.prepareStatement(query);
            preparedStatement.setInt(1, (int) id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
          List<User> userList;
          Connection connection = null;
          PreparedStatement preparedStatement = null;
          ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(Util.URL, Util.USERNAME, Util.PASSWORD);
            preparedStatement = Util.connection.prepareStatement("SELECT * FROM USERS");
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
            Util.statement.executeUpdate("DELETE FROM USERS");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
