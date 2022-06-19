import javafx.application.Application;
import javafx.stage.Stage;
import lk.ijse.hotelManagementSystem.entity.Student;
import lk.ijse.hotelManagementSystem.entity.Test;
import lk.ijse.hotelManagementSystem.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.Properties;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
//        Properties properties = new Properties();
//        try {
//            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.properties"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Student s1 = new Student();
//        s1.setId("S001");
//        s1.setName("Kamal");
//        session.save(s1);
        Test t1 = new Test();
        t1.setId("T001");
        t1.setName("AA");

       // session.save(t1);


        tx.commit();
    }
}
