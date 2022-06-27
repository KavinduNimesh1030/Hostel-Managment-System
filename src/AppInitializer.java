import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.hotelManagementSystem.entity.Test;
import lk.ijse.hotelManagementSystem.entity.User;
import lk.ijse.hotelManagementSystem.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
  /*      Session session = FactoryConfiguration.getInstance().getSession();
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
      Test t2 = new Test();
        t2.setId("T003");
        t2.setName("AA");

       session.save(t2);
           tx.commit();*/
//
//*/
       /* Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        User u1 = new User();
        u1.setUserId("U001");
        u1.setUserName("Kavindu");
        u1.setPassword("kn1234");
        session.save(u1);
        tx.commit();*/



        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/HotelManagementSystem/view/LoginForm.fxml"));
        Scene mainScene = new Scene(root);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Wholesale Shop");
        primaryStage.centerOnScreen();
        primaryStage.show();

    }

}
