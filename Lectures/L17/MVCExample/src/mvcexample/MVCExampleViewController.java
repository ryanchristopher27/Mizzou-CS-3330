/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvcexample;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Professor Wergeles
 */
public class MVCExampleViewController implements Initializable {

    @FXML
    private VBox root;
    @FXML
    private MenuItem addPerson;
    @FXML
    private MenuItem getEveryone;
    @FXML
    private MenuItem deleteEveryone;
    @FXML
    private TextArea textArea;
    @FXML
    private TextField textField; 
    
    MVCExampleModel model;
    
    
    @FXML
    public void addPersonButton(ActionEvent event){
        
        if (!textField.getText().isEmpty()) {
            if (model.addPeople(textField.getText())) {
                textArea.setText("Add person successfully");
            }
            else {
                textArea.setText("Please type a person");
            }
        }
    }
    
    @FXML
    public void getEveryoneButton(ActionEvent event){
        textArea.setText(model.getEveryone().toString());
    }
    
    @FXML
    public void deleteEveryoneButton(ActionEvent event){
        if (model.deleteEveryone()) {
            textArea.setText("Delete everyone was successful");
        }
        else {
            textArea.setText("Was not able to delete to everyone");
        }
    }
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = new MVCExampleModel();
    }    
    
}
