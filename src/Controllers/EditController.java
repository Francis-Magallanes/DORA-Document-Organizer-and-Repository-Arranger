package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.Optional;

public class EditController {

    @FXML
    public TextField lastNameField;

    @FXML
    public TextField givenNameField;

    @FXML
    public TextField middleNameField;

    private String filepathDATFile;
    private String filepathFolder;
    private String oldInfo;

    @FXML
    public void setup(String filepathFolder, String filename, String name){

        try {
            this.filepathFolder = filepathFolder;
            oldInfo = name;
            filepathDATFile = filepathFolder + "/" + filename;
            BufferedReader fileReader = new BufferedReader(new FileReader(new File(filepathDATFile )));
            lastNameField.setText(fileReader.readLine());
            givenNameField.setText(fileReader.readLine());
            middleNameField.setText(fileReader.readLine());
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void back(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    public void EditandSaveAction(ActionEvent event){

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setHeaderText("Confirmation to Edit");
        confirmation.setTitle("Confirmation to Edit");
        confirmation.setContentText("Do you want to edit the student info? Changes cannot be reverted back");
        Optional<ButtonType> decision = confirmation.showAndWait();

        if(decision.get().getText().equals("OK")){
            processEdit();
            DisplayInformation("The student info has been updated sucessfully");
            back(event);
        }


    }

    private void processEdit(){

        String newFolderpath = GetFileLocationToTransfer();
        try {
            FileWriter fileWriter = new FileWriter(filepathDATFile, false);//this will overwrite the file

            fileWriter.write(lastNameField.getText() + "\n");
            fileWriter.write(givenNameField.getText() + "\n");
            fileWriter.write(middleNameField.getText() + "\n");

            fileWriter.close();

            File fileDAT = new File(filepathDATFile);
            fileDAT.renameTo(new File(filepathFolder + "/" + lastNameField.getText()+ "_" + givenNameField.getText() + "_" + middleNameField.getText() +".dat"));

            File folderInfo = new File(filepathFolder);
            folderInfo.renameTo(new File(newFolderpath));


        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private String GetFileLocationToTransfer(){

        //this function will create the filepath which the file to be store;
        String location = "./root";
        String subfolder = "/" + (lastNameField.getText().toUpperCase().charAt(0));
        String subfolder2 = "/" + lastNameField.getText()+ "_" + givenNameField.getText() + "_" + middleNameField.getText();

        return location + subfolder+ subfolder2 ;
    }

    private void DisplayInformation(String infoText){
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setHeaderText("Success");
        info.setContentText(infoText);
        info.setTitle("Success");
        info.showAndWait();
    }

}
