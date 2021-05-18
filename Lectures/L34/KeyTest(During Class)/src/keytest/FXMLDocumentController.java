/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keytest;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Professor Wergeles
 */
public class FXMLDocumentController implements Initializable {
    
    
    private Stage stage;
    
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private VBox vBox;
    
    @FXML
    private TextArea textArea;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    
    public void ready(Stage stage) {
        this.stage = stage;
        
        this.stage.setResizable(false);
        
        textArea.setText("Hello World!");
        
        Image image = new Image(this.getClass().getResourceAsStream("car.jph"));
        
        ImageView imageView = new ImageView(image);
        imageView.setX(100);
        imageView.setY(100);
        
        anchorPane.getChildren().add(imageView);
        
        // NOTE: I added the event filter on the stage because of two things:
        // 1. It is the top most UI object so key presses from anywhere in the UI will be obtained.
        // 2. If I place it on something like the vBox it will work in this example only because
        //    I have a textArea in the UI.  If there are no elements in the UI that normally get
        //    key presses then putting the listener on a UI container other than the stage (or possibly the
        //    scene) won't work.
        
        stage.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            
            if ("DOWN".equals(event.getCode().toString())) {
                imageView.setY(imageView.getY() + 5);
            }
            else if ("UP".equals(event.getCode().toString())) {
                imageView.setY(imageView.getY() - 5);
            }
            else if ("LEFT".equals(event.getCode().toString())) {
                imageView.setX(imageView.getX() - 5);
            }
            else if ("RIGHT".equals(event.getCode().toString())) {
                imageView.setX(imageView.getX() + 5);
            }
            
            textArea.appendText(event.getCode() + "\n");
            
        });
        
    }
    
    @FXML
    private void handleClear(ActionEvent event) {
        System.out.println("Clear Button Pressed!"); 
    }  
    
}
