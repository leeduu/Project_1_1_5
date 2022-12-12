package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util util = new Util();
    Connection conn = Util.getConnection();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() throws SQLException {
        try {
            conn.setAutoCommit(false);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement st = Util.connection.createStatement();
            st.execute("CREATE TABLE IF NOT EXISTS users(" +
                    "user_name varchar(30) not null," +
                    "user_lastName varchar(30) not null," +
                    "user_age int not null," +
                    "user_id int PRIMARY KEY AUTO_INCREMENT)");
            conn.commit();
        } catch (ClassNotFoundException | SQLException e) {
            conn.rollback();
            throw new RuntimeException(e);
        } finally {
            conn.setAutoCommit(true);
        }
    }

    public void dropUsersTable() throws SQLException {
        try {
            conn.setAutoCommit(false);
            Statement st = Util.connection.createStatement();
            st.executeUpdate("DROP TABLE IF EXISTS users");
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
        } finally {
            conn.setAutoCommit(true);
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        try {
            conn.setAutoCommit(false);
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO users(user_name, user_lastName, user_age) VALUES ('" + name +
                    "','" + lastName + "','" + age + "')");
            System.out.println("User c именем " + name + " добавлен в базу данных");
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
        } finally {
            conn.setAutoCommit(true);
        }
    }

    public void removeUserById(long id) throws SQLException {
        try {
            conn.setAutoCommit(false);
            Statement st = Util.connection.createStatement();
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM users WHERE user_id = ?");
            preparedStatement.setInt(1, (int) id);
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
        } finally {
            conn.setAutoCommit(true);
        }
    }

    public List<User> getAllUsers() {
        List<User> userList;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = preparedStatement.executeQuery();
            userList = new ArrayList<>();
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

    public void cleanUsersTable() throws SQLException {
        try {
            conn.setAutoCommit(false);
            Statement st = Util.connection.createStatement();
            st.executeUpdate("DELETE FROM users");
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            conn.setAutoCommit(true);
        }
    }

}
