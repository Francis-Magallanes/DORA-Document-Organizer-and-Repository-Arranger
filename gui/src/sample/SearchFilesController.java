package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SearchFilesController {


    public TextField NameField, YearField;

    public void search(ActionEvent event) throws IOException{
        if ((NameField.getText().equals(""))){
            Alert info = new Alert(Alert.AlertType.WARNING);
            info.setHeaderText("Warning!");
            info.setContentText("Please input the name of the student as specified in the file.");
            info.setTitle("Warning");
            info.showAndWait();
        }
        else if (YearField.getText().equals("")){
            Alert info = new Alert(Alert.AlertType.WARNING);
            info.setHeaderText("Warning!");
            info.setContentText("Please input the year of graduation as specified in the file.");
            info.setTitle("Warning");
            info.showAndWait();
        }
        else{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/sample/EditAndDeleteWindow.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage window = new Stage();
            window.setScene(scene);
            window.setTitle("Add Files");
            window.show();
            EditAndDeleteController controller = fxmlLoader.getController();
            controller.NameField.setText(NameField.getText());
            controller.YearField.setText(YearField.getText());
            NameField.setText("");
            YearField.setText("");
        }
    }

    public void back(ActionEvent event) throws IOException {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
