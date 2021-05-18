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
public class AController extends Switchable implements Initializable {
    
    @FXML
    public TextField textField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML
    private void handleGoToB(ActionEvent event) {
        Switchable.switchTo("B");
    }
    
    @FXML
    private void handleGoToC(ActionEvent event) {
        Switchable.switchTo("C"); 
    }
}