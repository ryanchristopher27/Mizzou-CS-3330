/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package switcherexample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Professor Wergeles
 */
public class BController extends Switchable implements Initializable {

    @FXML
    private TextField bText; 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
    @FXML
    private void handleGoToA(ActionEvent event) {
        Switchable.switchTo("A");
        
        AController controller = (AController)getControllerByName("A");
        if (controller != null) {
            if(!bText.getText().equals("")){
                controller.textField.setText("Hello from: " + bText.getText());
            }
            else {
                controller.textField.setText("Hello A from B!");
            }
            
        }
    }
    
    @FXML
    private void handleGoToC(ActionEvent event) {   
        Switchable.switchTo("C"); 
    }    
}