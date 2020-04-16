package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.DecryptionClass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LoginController {

    @FXML public TextField Username;
    @FXML public PasswordField Password;
    @FXML public ImageView logo;

    private String username;
    private String password;
    public String question = "Name one of the developers of this software (First Names)";
    public String answer = "Jessica";
    public String answer2 = "Francis";
    public String answer3 = "John";


    @FXML
    public void initialize() throws IOException{
        logo.setImage(new Image("./assets/logo.png"));
        username = "admin";

        //this will get the password from .dat file
        BufferedReader fileReader = new BufferedReader(new FileReader(new File("./src/assets/password.dat")));
        password = DecryptionClass.ProcessDecryption(fileReader.readLine(),3);
        fileReader.close();

    }

    @FXML
    public void Login(ActionEvent event) throws IOException{

        if(!Username.getText().equals("")){

            if(!Password.getText().equals("")){

                if ((Username.getText().equals(username)) && Password.getText().equals(password)){
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/FXML/MainWindow.fxml"));
                    Parent root = fxmlLoader.load();
                    Scene scene = new Scene(root);
                    Stage window = new Stage();
                    window.setScene(scene);
                    window.setTitle("File Management System");
                    window.show();
                    MainController controller = fxmlLoader.getController();
                    ((Node)(event.getSource())).getScene().getWindow().hide();
                }
                else {
                    Alert info = new Alert(Alert.AlertType.WARNING);
                    info.setHeaderText("Warning!");
                    info.setContentText("Incorrect password or Username");
                    info.setTitle("Warning");
                    info.showAndWait();
                }

            }

            else DisplayNotification("Please Input Password");
        }

        else DisplayNotification("Please Input Username");
    }

    @FXML
    public void ForgetPassword(ActionEvent event) throws  IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/FXML/ForgetPasswordWindow.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage window = new Stage();
        window.setScene(scene);
        window.setTitle("Forget Password Window");
        window.show();
        ForgetPasswordController controller = fxmlLoader.getController();
        controller.AppendTexts(question,answer,answer2,answer3);

        exit(event);
    }

    @FXML
    public void exit(ActionEvent event) throws IOException {

        ((Node)(event.getSource())).getScene().getWindow().hide();

    }

    private void DisplayNotification(String warning){
        Alert info = new Alert(Alert.AlertType.WARNING);
        info.setHeaderText("No Username or Password");
        info.setContentText(warning);
        info.setTitle("No Username or Password");
        info.showAndWait();
    }
}
