package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.sql.SQLException;

import java.util.List;

public class Main extends UserDaoJDBCImpl {
    static UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        userDaoJDBC.createUsersTable();

        userDaoJDBC.saveUser("Anna", "Antonova", (byte) 11);
        userDaoJDBC.saveUser("Boris", "Bortov", (byte) 22);
        userDaoJDBC.saveUser("Clare", "Chase", (byte) 33);
        userDaoJDBC.saveUser("Denny", "Denisov", (byte) 44);

        userDaoJDBC.getAllUsers();
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();

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

