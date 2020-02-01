import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
        System.out.println("main "+Thread.currentThread().getName());
    }

    @Override
    public void start(Stage primaryStage) {
        System.out.println("start1 "+Thread.currentThread().getName());
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("SerialAssistant.fxml"));
            System.out.println("start2 "+Thread.currentThread().getName());
            primaryStage.setTitle("串口调试助手");
            primaryStage.getIcons().add(new Image("avator.png"));
            primaryStage.setScene(new Scene(root));
            System.out.println("start "+Thread.currentThread().getName());
            primaryStage.show();
            System.out.println("start "+Thread.currentThread().getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
