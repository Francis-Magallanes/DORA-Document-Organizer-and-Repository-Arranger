package sample;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class EditAndDeleteController {

    public TextField NameField, YearField;

    public void edit(ActionEvent event) throws IOException{
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setHeaderText("Information");
        info.setContentText("File Edited!");
        info.setTitle("Information");
        info.showAndWait();

        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    public void delete(ActionEvent event) throws  IOException{
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setHeaderText("Information");
        info.setContentText("File Deleted!");
        info.setTitle("Information");
        info.showAndWait();

        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    public void back(ActionEvent event) throws IOException{
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
