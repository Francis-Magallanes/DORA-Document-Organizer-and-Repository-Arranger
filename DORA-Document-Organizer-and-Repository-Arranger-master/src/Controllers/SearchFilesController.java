package Controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SearchFilesController {


    @FXML public TextField lastNameField;
    @FXML public TextField studentNameField;
    @FXML public Button addFilesButton;
    @FXML public Button deleteButton;
    @FXML public Button editButton;
    @FXML public Button retrieveButton;
    private String filepathFolder;
    private String filename;
    private String name;

    @FXML public void initialize(){
        revertDefault();
    }

    @FXML public void search(ActionEvent event) {

        if(!lastNameField.getText().equals("")){
            PerformSearch();
        }
        else{
            DisplayWarning("Please Input Last Name in the TextField");
        }

    }

    @FXML public void back(ActionEvent event){
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML public void addFilesAction(ActionEvent event){

        FileChooser FileChooser = new FileChooser();

        Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow());

        List<File> files = FileChooser.showOpenMultipleDialog(stage);

        if(!files.isEmpty()){
           transferFiles(filepathFolder,files);
           DisplaySuccessNotif("Files are sucessfully transferred");
        }

    }

    @FXML public void deleteAction(ActionEvent event){
        ButtonType deleteFilesButton = new ButtonType("Delete Files");
        ButtonType deleteStudentButton = new ButtonType("Delete Student Info");

        Alert option = new Alert(Alert.AlertType.INFORMATION,"Do you want to delete student info or delete files associated to the student info"
                ,deleteFilesButton,deleteStudentButton, ButtonType.CANCEL);

        Optional<ButtonType> decision = option.showAndWait();

        if(decision.get().getText().equals("Delete Files")){
            deleteFilesExecute(event);

        }
        else if(decision.get().getText().equals("Delete Student Info")){
            //this will delete teh student info
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do you want to delete this student info? Deleting this student cannot be reverted");
            Optional<ButtonType> confirmation = alert.showAndWait();

            if(confirmation.get().getText().equals("OK")){
                deleteDir(new File(filepathFolder));
                DisplaySuccessNotif("The student info is successfully deleted");
                revertDefault();
            }

        }

    }

    @FXML public void editAction (ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/FXML/EditWindow.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage window = new Stage();
        window.setResizable(false);
        window.setScene(scene);
        window.setTitle("D.O.R.A - Edit Student Info");
        window.initModality(Modality.APPLICATION_MODAL);
        window.show();
        EditController controller = fxmlLoader.getController();
        controller.setup(filepathFolder,filename,name);

        revertDefault();
    }

    @FXML public void RetrieveAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/FXML/RetrieveFilesWindow.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage window = new Stage();
        window.setResizable(false);
        window.setScene(scene);
        window.setTitle("D.O.R.A - Retrieve Files");
        window.initModality(Modality.APPLICATION_MODAL);
        window.show();
        RetrieveFilesController controller = fxmlLoader.getController();
        controller.setup(filepathFolder);
    }

    private void revertDefault(){
        addFilesButton.setDisable(true);
        deleteButton.setDisable(true);
        editButton.setDisable(true);
        retrieveButton.setDisable(true);
        studentNameField.setText("");
    }

    private void transferFiles(String filepathFolder, List<File> filesToTransfer){

        for(File f : filesToTransfer){
            f.renameTo(new File(filepathFolder+"/"+f.getName()));//transferring of files
        }

    }

    private void PerformSearch(){

        ArrayList<File> selectedFiles = new ArrayList<>();//this will store the files that have common query
        String folderToSearch = "./root/"+ lastNameField.getText().toUpperCase().charAt(0);
        File[] folderContents = new File(folderToSearch).listFiles();

        if(folderContents != null){

            for(File f : folderContents){
               if( f.getName().split("_")[0].toUpperCase().equals(lastNameField.getText().toUpperCase())){//this will get the info folder related to the query

                   selectedFiles.add(f);

               }

            }

            if(!selectedFiles.isEmpty()){
                DisplayChoose(selectedFiles);
            }
            else{
                DisplayWarning("There is no student info found in the repository. Please try another last name as query");
            }


        }
        else{
            DisplayWarning("The Directory is Empty. No Student Info Found");
        }


    }

    private void DisplayChoose(ArrayList<File> selectedFiles){

        Stage primaryStage = new Stage();
        VBox vBox = new VBox();
        Label label = new Label("Please choose one of the profiles");
        vBox.getChildren().add(label);

        ListView listView = new ListView(); //this will list the selected files make the user choose from it

        for(File f : selectedFiles){
            listView.getItems().add(f.getName());
        }

        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        listView.setOnMouseClicked(event -> {
            studentNameField.setText(listView.getSelectionModel().getSelectedItems().get(0).toString());
            addFilesButton.setDisable(false);
            deleteButton.setDisable(false);
            editButton.setDisable(false);
            retrieveButton.setDisable(false);
            lastNameField.setText("");
            name = studentNameField.getText();
            filepathFolder = "./root/"+ studentNameField.getText().toUpperCase().charAt(0) + "/"+ studentNameField.getText() ;
            filename = studentNameField.getText() + " .dat";
            primaryStage.hide();
        });

        vBox.getChildren().add(listView);
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.TOP_LEFT);
        Scene scene = new Scene(vBox,400,200);

        primaryStage.setTitle("Choose one of the Profiles");
        primaryStage.setScene(scene);
        primaryStage.initModality(Modality.APPLICATION_MODAL);

        Platform.runLater(primaryStage::show);

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

    private void deleteFilesExecute(ActionEvent event){

        FileChooser FileChooser = new FileChooser();
        FileChooser.setInitialDirectory(new File(filepathFolder));
        Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow());

        List<File> files = FileChooser.showOpenMultipleDialog(stage);

        if(!files.isEmpty()){

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do you want to delete these files? Deleting these files cannot be reverted");
            Optional<ButtonType> confirmation = alert.showAndWait();

            if(confirmation.get().getText().equals("OK")){

                for(File f : files){
                    f.delete();
                }
                DisplaySuccessNotif("The selected files are now deleted");
            }

        }

    }

    private void deleteDir(File file) {
        //this code is from the stack overflow
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                deleteDir(f);
            }
        }
        file.delete();
    }
}
