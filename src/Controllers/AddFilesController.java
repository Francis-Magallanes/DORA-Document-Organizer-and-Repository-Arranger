package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;

public class AddFilesController {

    public Button Browse, AddFile, Back;
    private List<File> files;

    @FXML
    public TextField lastNameField, givenNameField, middleNameField;

    @FXML
    public TextArea filePathTextArea;

    @FXML
    public void browseFile(ActionEvent event){

        FileChooser FileChooser = new FileChooser();


        Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow());

        files = FileChooser.showOpenMultipleDialog(stage);

        if(!files.isEmpty()){
            filePathTextArea.setText(ListFiles());
        }
    }

    @FXML
    public void AddFileToDatabase(ActionEvent event) {

        if(lastNameField.getText().equals("")){
            DisplayWarning("Please input Last Name");
        }
        else if (givenNameField.getText().equals("")){
            DisplayWarning("Please input Given Name");
        }
        else if (middleNameField.getText().equals("")){
            DisplayWarning("Please input Middle Name");
        }
        else if (filePathTextArea.getText().equals("")){
            DisplayWarning("Please select file");
        }
        else{
            AddFiles(event);
        }

    }

    @FXML
    public void back(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    private void AddFiles(ActionEvent event){

        //this will get first the file location that will the file be transferred to
        String filepathFolder = GetFileLocationToTransfer();
        String nameDATFile = lastNameField.getText()+ "_" + givenNameField.getText() + "_" + middleNameField.getText();

        if(!isInfoExist(nameDATFile)){

            File folder = new File(filepathFolder);
            folder.mkdir();

            for(File src : files){

                if(!src.renameTo( new File (filepathFolder + "/" + src.getName()) )){
                    DisplayWarning("The file is currently open. Please close the file that is being transferred");
                    deleteDir(folder);
                    return;

                }

            }

            CreateDATFile(filepathFolder, nameDATFile);//creating the dat file
            DisplayInformation("The File is Transferred and Added in the Respository. New Student Info is added");
            back(event);
        }
        else{
            DisplayWarning("The inputed info is already exist");
        }

    }

    private String ListFiles(){

        StringBuilder stringBuilder = new StringBuilder();

       for(File f : files){
           stringBuilder.append(f.getAbsolutePath()).append("\n");
       }

       return stringBuilder.toString();
    }

    private String GetFileLocationToTransfer(){

        //this function will create the filepath which the file to be store;
        String location = "./root";
        String subfolder = "/" + (lastNameField.getText().toUpperCase().charAt(0));
        String subfolder2 = "/" + lastNameField.getText()+ "_" + givenNameField.getText() + "_" + middleNameField.getText();

        return location + subfolder+ subfolder2 ;
    }

    private void CreateDATFile (String filepath, String nameFile){

        try {
            PrintStream datFile = new PrintStream(filepath + "/"+ nameFile + " .dat");
            datFile.println(lastNameField.getText());
            datFile.println(givenNameField.getText());
            datFile.println(middleNameField.getText());
            datFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void DisplayWarning(String warning){
        Alert info = new Alert(Alert.AlertType.WARNING);
        info.setHeaderText("Warning!");
        info.setContentText(warning);
        info.setTitle("Warning");
        info.showAndWait();
    }

    private void DisplayInformation(String infoText){
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setHeaderText("Files are added");
        info.setContentText(infoText);
        info.setTitle("Files are Added");
        info.showAndWait();
    }

    private boolean isInfoExist(String name){
        File file = new File("./root/"+name.toUpperCase().charAt(0));
        File[] directory = file.listFiles();
        boolean found = false;

        //this is only linear search
        for(File f : directory){
            if(name.equals(f.getName())){
                found =true;
                break;
            }
        }

        return found;
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
