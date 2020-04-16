package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ForgetPasswordController {

    @FXML public Label SecurityQuestion;
    @FXML public TextField Answer;
    @FXML public Button Submit;

    public String CheckAnswer;
    public String CheckAnswer2;
    public String CheckAnswer3;

    @FXML
    public void AppendTexts(String question, String answer, String answer2, String answer3) throws IOException{
        SecurityQuestion.setText(question);
        CheckAnswer = answer;
        CheckAnswer2 = answer2;
        CheckAnswer3 = answer3;
    }

    @FXML
    public void Check(ActionEvent event) throws IOException{
        if ((CheckAnswer.toUpperCase().equals(Answer.getText().toUpperCase())) || (CheckAnswer2.toUpperCase().equals(Answer.getText().toUpperCase())) || (CheckAnswer3.toUpperCase().equals(Answer.getText().toUpperCase()))){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/FXML/ChangePasswordUnderForgetWindow.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage window = new Stage();
            window.setResizable(false);
            window.setScene(scene);
            window.setTitle("D.O.R.A - Change Password");
            window.initModality(Modality.APPLICATION_MODAL);
            window.show();

            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        else{
            Alert info = new Alert(Alert.AlertType.WARNING);
            info.setHeaderText("Warning!");
            info.setContentText("Incorrect Answer!");
            info.setTitle("Warning");
            info.showAndWait();
        }
    }

    @FXML
    public void Back(ActionEvent event) throws IOException{
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

}
