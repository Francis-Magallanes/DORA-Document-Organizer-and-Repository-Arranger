package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/LoginWindow.fxml"));
        primaryStage.setTitle("D.O.R.A - Login");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 300, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
