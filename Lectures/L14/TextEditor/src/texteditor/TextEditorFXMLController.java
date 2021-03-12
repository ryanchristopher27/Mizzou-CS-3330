/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rdash
 */
public class TextEditorFXMLController implements Initializable {

    /*
        The @FXML annotation is used to tag 'nonpublic' controller member
        fields and handler methods (event listeners) for use by
        FXML Markup
    */
    @FXML
    private TextArea textArea;
    
    /* Bugs on the quiz
        1) Must have @FXML
        2) Must not declare yourself, let the FXML do it
        3) Must be private
        4)FX ID must be case sensitive
    */
    @FXML
    private VBox root;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void handleOpen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        
        Stage stage = (Stage) root.getScene().getWindow();
        
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt", "*.java", "*.html"));
        
        File file = fileChooser.showOpenDialog(stage);
        
        if (file != null) {
            BufferedReader bufferedReader = null;
            
            String document = "";
            String line = "";
            
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                
                while ((line = bufferedReader.readLine()) != null) {
                    document += line + "\n";
                }
                
                textArea.setText(document);
            }
            catch (FileNotFoundException ex) {
                Logger.getLogger(TextEditorFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                displayExceptionAlert(ex);
            }
            catch (IOException ex) {
                Logger.getLogger(TextEditorFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Catch "all" for exceptions
            catch (Exception ex) {
                Logger.getLogger(TextEditorFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                displayExceptionAlert(ex);
            }
            finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException ex) {
                        Logger.getLogger(TextEditorFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        displayExceptionAlert(ex);
                    }
                }
            }
            
        }
        
        fileChooser.showOpenDialog(stage);
    }
    
    @FXML
    private void handleSave(ActionEvent Event) {
        FileChooser fileChooser = new FileChooser();
        
        Stage stage = (Stage) root.getScene().getWindow();
        
        fileChooser.showSaveDialog(stage);
    }
    
    /*
        Model-View-Controller (MVC)
    
        * View -> FXML File (UI - Code)
    
        * Controller -> Java Class handles the events for UI (Middle Man)
    
        * Model -> Domain Objects *, on java side, that connect to the view
            through the controller
            Domain Object = Data of Application
    */
    
}
