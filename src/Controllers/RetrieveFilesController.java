package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;


public class RetrieveFilesController {

    @FXML public TextArea filesToBeTransferredArea;
    @FXML public TextArea fileLocationTransferArea;
    private List<File> fileToBeTransferred;
    private File fileLocationTransfer;
    private String filepathFolder;



    @FXML
    public void BrowseFilesTransferAction(ActionEvent event){

        FileChooser FileChooser = new FileChooser();
        FileChooser.setInitialDirectory(new File(filepathFolder));
        Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow());

        fileToBeTransferred = FileChooser.showOpenMultipleDialog(stage);

        try{

            if(!fileToBeTransferred.isEmpty()){
                filesToBeTransferredArea.setText(ListFiles());
            }

        }catch (NullPointerException ignored){

        }


    }

    @FXML
    public void BrowseFileLocationAction (ActionEvent event){
        DirectoryChooser FileChooser = new DirectoryChooser();

        Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow());

        fileLocationTransfer = FileChooser.showDialog(stage);

        try{
            if(fileLocationTransfer.exists()){
                fileLocationTransferArea.setText(fileLocationTransfer.getAbsolutePath());
            }
        }catch (NullPointerException ignored){

        }


    }

    @FXML
    public void RetrieveFileAction(ActionEvent event){

        if(!filesToBeTransferredArea.getText().isEmpty()){

            if(!fileLocationTransferArea.getText().isEmpty()){
                PerformTransferFiles();
                DisplaySuccessNotif("The selected files are successfully retrieved");
                back(event);
            }
            else{
                DisplayWarning("Please set the location where the selected files will be saved");
            }
        }
        else{
            DisplayWarning("Please set first the files to be retrieved");
        }
    }

    @FXML
    public void back(ActionEvent event){
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    public void setup(String filepathFolder){
        this.filepathFolder = filepathFolder;
    }

    private void PerformTransferFiles(){

        for(File f : fileToBeTransferred){

            try{
                File temp = new File(fileLocationTransfer.getAbsolutePath() + "/" + f.getName() );
                Files.copy(f.toPath(),temp.toPath(),StandardCopyOption.REPLACE_EXISTING);
            }catch (IOException io){
                io.printStackTrace();
            }

        }


    }

    private String ListFiles(){

        StringBuilder stringBuilder = new StringBuilder();

        for(File f : fileToBeTransferred){
            stringBuilder.append(f.getAbsolutePath()).append("\n");
        }

        return stringBuilder.toString();
    }

    private void DisplayWarning(String warning){
        Alert info = new Alert(Alert.AlertType.WARNING);
        info.setHeaderText("Warning!");
        info.setContentText(warning);
        info.setTitle("Warning");
        info.showAndWait();
    }

    private void DisplaySuccessNotif(String message){
        Alert info = new Alert(Alert.AlertType.WARNING);
        info.setHeaderText("Success!");
        info.setContentText(message);
        info.setTitle("Success!");
        info.showAndWait();
    }

}
