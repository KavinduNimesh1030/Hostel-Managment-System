package lk.ijse.hotelManagementSystem.util;


import lk.ijse.hotelManagementSystem.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() {
        try {
            Configuration configuration = new Configuration();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            configuration.addAnnotatedClass(Room.class).addAnnotatedClass(Student.class).addAnnotatedClass(Reserve.class).addAnnotatedClass(User.class).addAnnotatedClass(Test.class);
            sessionFactory =  configuration.buildSessionFactory(serviceRegistry);

        } catch (Exception e){
            e.printStackTrace();
            throw  new RuntimeException("There is issue in factroy configuration");
        }

    }

    public static FactoryConfiguration getInstance() {
        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration()
                : factoryConfiguration;
    }
    public Session getSession() {
        return sessionFactory.openSession();
    }
}
