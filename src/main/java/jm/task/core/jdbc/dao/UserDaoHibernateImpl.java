package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final static SessionFactory daoSessionFactory = Util.getSessionFactory();
    private static Connection connect = Util.getConnection();

    public UserDaoHibernateImpl() {
    }

    public void createUsersTable() throws SQLException {
        try {
            daoSessionFactory.openSession();
            connect.setAutoCommit(false);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement st = Util.connection.createStatement();
            st.execute("CREATE TABLE IF NOT EXISTS users(" +
                    "user_name varchar(30) not null," +
                    "user_lastName varchar(30) not null," +
                    "user_age int not null," +
                    "id int)");
            connect.commit();
        } catch (ClassNotFoundException | SQLException e) {
            connect.rollback();
            throw new RuntimeException(e);
        } finally {
            connect.setAutoCommit(true);
        }
    }

    public void dropUsersTable() throws SQLException {
        try {
            daoSessionFactory.openSession();
            connect.setAutoCommit(false);
            Statement st = Util.connection.createStatement();
            st.executeUpdate("DROP TABLE IF EXISTS users");
            connect.commit();
        } catch (SQLException e) {
            connect.rollback();
            e.printStackTrace();
        } finally {
            connect.setAutoCommit(true);
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        try {
            daoSessionFactory.openSession();
            connect.setAutoCommit(false);
            Statement st = connect.createStatement();
            st.executeUpdate("INSERT INTO users(user_name, user_lastName, user_age) VALUES ('" + name +
                    "','" + lastName + "','" + age + "')");
            System.out.println("User c именем " + name + " добавлен в базу данных");
            connect.commit();
        } catch (SQLException e) {
            connect.rollback();
            e.printStackTrace();
        } finally {
            connect.setAutoCommit(true);
        }
    }

    public void removeUserById(long id) throws SQLException {
        try {
            daoSessionFactory.openSession();
            connect.setAutoCommit(false);
            Statement st = Util.connection.createStatement();
            PreparedStatement preparedStatement = connect.prepareStatement("DELETE FROM users WHERE id = ?");
            preparedStatement.setInt(1, (int) id);
            connect.commit();
        } catch (SQLException e) {
            connect.rollback();
            e.printStackTrace();
        } finally {
            connect.setAutoCommit(true);
        }
    }

    public List<User> getAllUsers() {
        List<User> userList;
        try {
            daoSessionFactory.openSession();
            PreparedStatement preparedStatement = connect.prepareStatement("SELECT * FROM users");
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
            daoSessionFactory.openSession();
            connect.setAutoCommit(false);
            Statement st = Util.connection.createStatement();
            st.executeUpdate("DELETE FROM users");
            connect.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connect.rollback();
        } finally {
            connect.setAutoCommit(true);
        }
    }
}
