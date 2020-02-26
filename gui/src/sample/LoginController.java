package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    public TextField Username;
    public PasswordField Password;

    public String username;
    public String password;

    public void initialize(){
        username = "Admin";
        password = "12345";
    }

    public void Login(ActionEvent event) throws IOException{
        if ((Username.getText().equals(username)) && Password.getText().equals(password)){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/sample/sample.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage window = new Stage();
            window.setScene(scene);
            window.setTitle("File Management System");
            window.show();
            Controller controller = fxmlLoader.getController();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        else {
            Alert info = new Alert(Alert.AlertType.WARNING);
            info.setHeaderText("Warning!");
            info.setContentText("Incorrect password");
            info.setTitle("Warning");
            info.showAndWait();
        }
    }
}
