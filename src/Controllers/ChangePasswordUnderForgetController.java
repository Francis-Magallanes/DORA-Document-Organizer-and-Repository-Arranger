package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.EncryptionClass;

import java.io.FileWriter;
import java.io.IOException;

public class ChangePasswordUnderForgetController {

    @FXML
    PasswordField newPasswordField;

    @FXML
    PasswordField retypeNewPasswordField;

    @FXML
    public void ChangePasswordAction(ActionEvent event){

        if(!newPasswordField.getText().equals("")){

            if(!retypeNewPasswordField.getText().equals("")){

                if(newPasswordField.getText().equals(retypeNewPasswordField.getText())){

                    ProceedChangePassword(event);
                }
                else{
                    DisplayWarning("Please retype your new password in the re-type password field correctly");
                }
            }
            else{
                DisplayNotification("Please re-type your new password in the corresponding field");
            }

        }
        else{
            DisplayNotification("Please type your new password");
        }
    }

    private void ProceedChangePassword(ActionEvent event){

        try {
            FileWriter fileWriter = new FileWriter("./src/assets/password.dat",false);//this will overwrite the file
            fileWriter.write(EncryptionClass.ProcessEncryption(newPasswordField.getText(),3));
            fileWriter.close();

            DisplaySuccesfulNotification();

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/FXML/LoginWindow.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage window = new Stage();
            window.setResizable(false);
            window.setScene(scene);
            window.setTitle("D.O.R.A - Login");
            window.initModality(Modality.APPLICATION_MODAL);
            window.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void cancel(ActionEvent event) throws IOException{
        ((Node)(event.getSource())).getScene().getWindow().hide();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/FXML/LoginWindow.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage window = new Stage();
        window.setResizable(false);
        window.setScene(scene);
        window.setTitle("D.O.R.A - Login");
        window.initModality(Modality.APPLICATION_MODAL);
        window.show();
    }

    private void DisplayNotification(String warning){
        Alert info = new Alert(Alert.AlertType.WARNING);
        info.setHeaderText("Empty Input");
        info.setContentText(warning);
        info.setTitle("Empty Input");
        info.showAndWait();
    }

    private void DisplayWarning(String warning){
        Alert info = new Alert(Alert.AlertType.WARNING);
        info.setHeaderText("Wrong Input");
        info.setContentText(warning);
        info.setTitle("Wrong Input");
        info.showAndWait();
    }

    private void DisplaySuccesfulNotification(){
        Alert info = new Alert(Alert.AlertType.WARNING);
        info.setHeaderText("Sucess");
        info.setContentText("The password is sucessfully changed.");
        info.setTitle("Sucess");
        info.showAndWait();
    }
}
