package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import sample.DecryptionClass;
import sample.EncryptionClass;

import java.io.*;

public class ChangePasswordController {

    @FXML public PasswordField oldPasswordField;
    @FXML public PasswordField newPasswordField;
    @FXML public PasswordField retypePassword;
    private String oldPasswordString;

    @FXML void initialize() throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(new File("./src/assets/password.dat")));
        oldPasswordString = DecryptionClass.ProcessDecryption(fileReader.readLine(),3);
        fileReader.close();
    }


    @FXML
    public void ChangePasswordAction (ActionEvent event){

        if(!oldPasswordField.getText().equals("")){

            if(!newPasswordField.getText().equals("")){

                if(!retypePassword.getText().equals("")){

                    ChangePasswordProceed(event);

                }
                else DisplayNotification("Please re-type your new password");

            }
            else DisplayNotification("Please input the New Password");

        }
        else DisplayNotification("Please input the Old Password");


    }

    private void ChangePasswordProceed (ActionEvent event){

        if(oldPasswordField.getText().equals(oldPasswordString)){

            if(newPasswordField.getText().equals(retypePassword.getText())){

                try {
                    FileWriter fileWriter = new FileWriter("./src/assets/password.dat",false);//this will overwrite the file
                    fileWriter.write(EncryptionClass.ProcessEncryption(newPasswordField.getText(),3));
                    fileWriter.close();

                    DisplaySuccesfulNotification();

                    cancel(event);//this will close the window
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            else{
                DisplayWarning("You have entered wrong password in the re-type password field. Please re-type again your new password");
            }

        }
        else{
            DisplayWarning("You have entered wrong old password, please retry again");
        }
    }

    @FXML
    public void cancel(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
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
