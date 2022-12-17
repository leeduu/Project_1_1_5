package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserDao userDaoHibernate = new UserDaoHibernateImpl();

    public void createUsersTable() {
        try {
            userDaoHibernate.createUsersTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try {
            userDaoHibernate.dropUsersTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            userDaoHibernate.saveUser(name, lastName, age);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try {
            userDaoHibernate.removeUserById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        return userDaoHibernate.getAllUsers();
    }

    public void cleanUsersTable() {
        try {
            userDaoHibernate.cleanUsersTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
