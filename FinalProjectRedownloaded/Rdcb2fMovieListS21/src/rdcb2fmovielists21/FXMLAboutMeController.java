/*
 * Name: Ryan Christopher
 * Pawprint: rdcb2f
 * Class: CS 3330 Object Oriented Programming
 * Final Project: Movie List
 */
package rdcb2fmovielists21;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Ryan Christopher
 */
public class FXMLAboutMeController extends Switchable implements Initializable, Switcher {

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    

    @FXML
    @Override
    public void goToA(ActionEvent event) {
        Switchable.switchTo("FXMLMovieList");
    }

    @FXML
    @Override
    public void goToB(ActionEvent event) {
        Switchable.switchTo("FXMLMovieSelector");
    }
    
}
