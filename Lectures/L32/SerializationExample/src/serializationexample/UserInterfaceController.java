/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serializationexample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Professor Wergeles
 * 
 * @references
 *      1) http://www.tutorialspoint.com/java/java_serialization.htm
 * 
 */
public class UserInterfaceController implements Initializable {

    private Stage stage;
    
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField ageTextField;
    @FXML
    private ComboBox genderComboBox;
    
    Person person; 
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        person = new Person(); 
    }
    
    public void ready(Stage stage) {
        this.stage = stage;
        
        genderComboBox.getItems().add("female");
        genderComboBox.getItems().add("male");
    }
    
    @FXML
    public void handleOpen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            FileInputStream fileIn; 
            try {
                fileIn = new FileInputStream(file.getPath());
                ObjectInputStream in = new ObjectInputStream(fileIn); 
                
                person = (Person) in.readObject();
                
                in.close(); 
                fileIn.close(); 
                fillFormFromPerson(person); 
                
            } catch (FileNotFoundException ex) {
                String message = "File not found exception occured while opening " + file.getPath(); 
                displayExceptionAlert(message, ex); 
                
            } catch (IOException ex) {
                String message = "IO exception occured while opening " + file.getPath(); 
                displayExceptionAlert(message, ex);
                
            } catch (ClassNotFoundException ex) {
                String message = "Class not found exception occured while opening " + file.getPath(); 
                displayExceptionAlert(message, ex); 
            }
            
            
            
        }
    }
    
    @FXML
    public void handleSave(ActionEvent event) {
        person = createPersonFromFormData(); 
        
        if(person == null){
            return; 
        }
        
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            
            try {
                FileOutputStream fileOut = new FileOutputStream(file.getPath());
                ObjectOutputStream out = new ObjectOutputStream(fileOut); 
                
                out.writeObject(person);
                out.close(); 
                fileOut.close(); 
                
            } catch (FileNotFoundException ex) {
                String message = "File not found exception occured while saving to " + file.getPath(); 
                displayExceptionAlert(message, ex); 
                
            } catch (IOException ex) {
                String message = "IOException occured while saving to " + file.getPath();
                displayExceptionAlert(message, ex);
                
            }
        }        
    }
    
    @FXML
    public void handleClose(ActionEvent event) {
        stage.close();
    }
    
    @FXML
    public void handleAbout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Serialization Example");
        alert.setContentText("This application was developed by Nickolas Wergeles for CS3330 at the University of Missouri.");
        
        TextArea textArea = new TextArea("This example illustrates how to serialize and deserialze objects.");
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(textArea, 0, 0);
        alert.getDialogPane().setExpandableContent(expContent);
        
        alert.showAndWait();
    }
    
    private Person createPersonFromFormData() {
        Person p = new Person();
        
        p.setFirstName(firstNameTextField.getText());
        p.setLastName(lastNameTextField.getText());
        
        String ageStr = ageTextField.getText();
        Integer age;
        try {
            age = Integer.parseInt(ageStr);
        } catch (Exception ex) {
            displayErrorAlert("The age value must be an integer.");
            return null;
        }
        p.setAge(age);
        
        if (genderComboBox.getValue() != null &&  !genderComboBox.getValue().toString().isEmpty()) {
            p.setGender(genderComboBox.getValue().toString());
        } else {
            displayErrorAlert("A gender must be selected.");
            return null;
        }

        return p;
    }
    
    private void fillFormFromPerson(Person person) {
        firstNameTextField.setText(person.getFirstName());
        lastNameTextField.setText(person.getLastName());
        ageTextField.setText(Integer.toString(person.getAge()));
        genderComboBox.setValue(person.getGender());
    }
    
    private void displayErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        //alert.setHeaderText("Error!");
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    private void displayExceptionAlert(String message, Exception ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exception Dialog");
        alert.setHeaderText("Exception!");
        alert.setContentText(message);

        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }      
}
