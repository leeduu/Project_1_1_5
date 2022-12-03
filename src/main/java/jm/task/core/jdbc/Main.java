package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;

public class Main extends UserServiceImpl {
    static User user = new User();
    static Util util = new Util();
    static UserDao userDao = new UserDaoJDBCImpl();

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
//User1
        try {
            userDao.saveUser("Anna", "Antonova", (byte) 11);
            user.setName("Anna");
            System.out.println("User с именем – " + user.getName() + " добавлен в базу данных");
        } catch (Exception e) {
            System.out.println("Ошибка при добавлении пользователя " + user.getName() + " в базу");
        }
//User2
        try {
            userDao.saveUser("Bella", "Berezkina", (byte) 22);
            user.setName("Bella");
            System.out.println("User с именем – " + user.getName() + " добавлен в базу данных");
        } catch (Exception e) {
            System.out.println("Ошибка при добавлении пользователя " + user.getName() + " в базу");
        }
//User3
        try {
            userDao.saveUser("Chloe", "Chekhova", (byte) 33);
            user.setName("Chloe");
            System.out.println("User с именем – " + user.getName() + " добавлен в базу данных");
        } catch (Exception e) {
            System.out.println("Ошибка при добавлении пользователя " + user.getName() + " в базу");
        }
//User4
        try {
            userDao.saveUser("Denny", "Denisov", (byte) 44);
            user.setName("Denny");
            System.out.println("User с именем – " + user.getName() + " добавлен в базу данных");
        } catch (Exception e) {
            System.out.println("Ошибка при добавлении пользователя " + user.getName() + " в базу");
        }

        Connection connection = util.getMySQLConnection();
        Statement statement = connection.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS USERS " +
                "(name VARCHAR(255), " +
                " lastName VARCHAR(255), " +
                " age INTEGER)";
        statement.executeUpdate(sql);
        ResultSet rs = statement.executeQuery("SELECT * FROM USERS");
        while (rs.next()) {
            String name = rs.getString(1);
            String lastName = rs.getString(2);
            byte age = rs.getByte(3);
            System.out.println(name + lastName + age);
        }
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
        connection.close();
    }

    @Override
    public void createUsersTable() {
    }

    @Override
    public void dropUsersTable() {
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
    }

    @Override
    public void removeUserById(long id) {
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {
    }
}

