package sample;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class AddFilesController {

    public Button Browse, AddFile, Back;
    public AnchorPane AnchorPane;
    public TextField FileField, NameField, YearField;

    public void browseFile(ActionEvent event) throws IOException{

        FileChooser FileChooser = new FileChooser();

        Stage stage = (Stage) AnchorPane.getScene().getWindow();

        File file = FileChooser.showOpenDialog(stage);

        if (file != null){
            FileField.setText(file.getAbsolutePath());
        }
        else {
            Alert info = new Alert(Alert.AlertType.WARNING);
            info.setHeaderText("Warning!");
            info.setContentText("The file that you have selected is invalid! Please select a different file!");
            info.setTitle("Warning");
            info.showAndWait();
        }
    }

    public void AddFileToDatabase(ActionEvent event) throws IOException{
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
        else if (FileField.getText().equals("")){
            Alert info = new Alert(Alert.AlertType.WARNING);
            info.setHeaderText("Warning!");
            info.setContentText("Please browse a file first!");
            info.setTitle("Warning");
            info.showAndWait();
        }
        else {
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setHeaderText("Information");
            info.setContentText("File Added!");
            info.setTitle("Information");
            info.showAndWait();

            YearField.setText("");
            NameField.setText("");
            FileField.setText("");
        }
    }

    public void back(ActionEvent event) throws IOException{
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
