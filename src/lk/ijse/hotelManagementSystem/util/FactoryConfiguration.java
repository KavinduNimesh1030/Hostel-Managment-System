package lk.ijse.hotelManagementSystem.util;

import lk.ijse.hotelManagementSystem.entity.Student;
import lk.ijse.hotelManagementSystem.entity.Test;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;


public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Properties properties = new Properties();
        try {
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Configuration configuration = new Configuration();

        sessionFactory =  configuration.mergeProperties(properties).addResource("lk/ijse/hotelManagementSystem/entity/Test.hbm.xml").addResource("lk/ijse/hotelManagementSystem/entity/Student.hbm.xml").addResource("lk/ijse/hotelManagementSystem/entity/Room.hbm.xml").addResource("lk/ijse/hotelManagementSystem/entity/Reserve.hbm.xml").buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() {
        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration()
                : factoryConfiguration;
    }
    public Session getSession() {
        return sessionFactory.openSession();
    }
}
