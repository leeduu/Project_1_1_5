package jm.task.core.jdbc.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.*;

public class Util {

    //static SessionFactory sessionFactory = (SessionFactory) HibernateUtil.getSession();

    public static Connection connection = null;
    public static Statement statement = null;

    public static void getConnection() {

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Naushniki2007");
            statement = connection.createStatement();
            //connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /*public class HibernateUtil {

        final static StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        private static final SessionFactory sessionFactory;

        static {
            try {
                sessionFactory = new MetadataSources(registry).buildMetadata()
                        .buildSessionFactory();
            } catch (Throwable ex) {
                throw new ExceptionInInitializerError(ex);
            }
        }

    public static Session getSession() throws HibernateException {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (org.hibernate.HibernateException he) {
            session = sessionFactory.openSession();
        }
        return session;
    }*/
}
