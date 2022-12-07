package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.SQLException;

import java.util.List;

public class Main extends UserServiceImpl {
    static UserServiceImpl userService = new UserServiceImpl();


    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        userService.dropUsersTable();
        userService.createUsersTable();

        userService.saveUser("Anna", "Antonova", (byte) 11);
        userService.saveUser("Boris", "Bortov", (byte) 22);

        userService.getAllUsers();

        userService.removeUserById(1);
        userService.getAllUsers();

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

