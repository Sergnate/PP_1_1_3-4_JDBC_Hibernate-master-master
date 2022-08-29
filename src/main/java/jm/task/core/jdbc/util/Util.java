package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    protected static final String URL = "jdbc:mysql://localhost:3306/usersDB?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false";
    protected static final String USERNAME = "root";
    protected static final String PASSWORD = "Anya1995$";
    private static SessionFactory sessionFactory;

    public static Connection connection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }


    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            Configuration cfg = new Configuration()
                    .setProperty(Environment.URL, URL)
                    .setProperty(Environment.USER, USERNAME)
                    .setProperty(Environment.PASS, PASSWORD)
                    .setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread")
                    .addAnnotatedClass(User.class);

            ServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .applySettings(cfg.getProperties()).build();

            sessionFactory = cfg.buildSessionFactory(registry);
        }
        return sessionFactory;
    }
}

