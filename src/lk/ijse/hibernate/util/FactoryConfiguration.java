package lk.ijse.hibernate.util;

import lk.ijse.hibernate.entity.Login;
import lk.ijse.hibernate.entity.Reservation;
import lk.ijse.hibernate.entity.Room;
import lk.ijse.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/

// Singleton class
// This class is created to configure a SessionFactory
public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private final SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Properties properties = new Properties();
        try {
            //load that hibernate.properties file to a util Properties reference
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.properties"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Then, configure that util Properties file to SessionFactory
        Configuration configuration = new Configuration().mergeProperties(properties)
                .addAnnotatedClass(Login.class).addAnnotatedClass(Student.class).addAnnotatedClass(Reservation.class).addAnnotatedClass(Room.class);
        // build a SessionFactory and assign it to sessionFactory reference
        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() {
        return factoryConfiguration == null ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession() {
        // Get a new session from SessionFactory and will return it
        return sessionFactory.openSession();
    }
}
