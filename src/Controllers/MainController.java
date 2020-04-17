package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

   @FXML public Button Add;
   @FXML public ImageView logo;

   public void initialize() throws IOException{
       logo.setImage(new Image("./assets/logo.png"));
   }

   @FXML
    public void addFile(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/FXML/AddFilesWindow.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage window = new Stage();
        window.setResizable(false);
        window.setScene(scene);
        window.setTitle("D.O.R.A - Add Files");
       window.initModality(Modality.APPLICATION_MODAL);
        window.show();
        AddFilesController controller = fxmlLoader.getController();
    }

    @FXML
    public void searchFile(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/FXML/SearchFilesWindow.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage window = new Stage();
        window.setResizable(false);
        window.setScene(scene);
        window.setTitle("D.O.R.A - Search Files");
        window.initModality(Modality.APPLICATION_MODAL);
        window.show();
        SearchFilesController controller = fxmlLoader.getController();
    }

    @FXML
    public void ChangePassword(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/FXML/ChangePasswordWindow.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage window = new Stage();
        window.setResizable(false);
        window.setScene(scene);
        window.setTitle("D.O.R.A - Change Password");
        window.initModality(Modality.APPLICATION_MODAL);
        window.show();
        ChangePasswordController controller = fxmlLoader.getController();
    }

    @FXML
    public void exit(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
