/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickpointanim;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Professor Wergeles
 */
public class UserInterfaceController implements Initializable {

    private Stage stage;
    private ArrayList<Animation> animations;
    
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private Text countText;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        animations = new ArrayList<>();
    }
    
    public void ready(Stage stage) {
        this.stage = stage; 
        countText.setText("0"); 
        
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we){
                System.out.println("Stage is closing"); 
                
                for(Animation animation : animations){
                    animation.end(); 
                }
            }
        }); 
    }
    
    @FXML
    private void handleMouseClick(MouseEvent event) {
        System.out.println(event.getX() + ", " + event.getY()); 
        
        Animation animation = new Animation(anchorPane, event.getX(), event.getY()); 
        
        animations.add(animation); 
        
//        animation.start(); 
        
        // Only need this if using "implements Runnable" interface instead of
        // the "extends Thread" superclass on the Animation class
        Thread t = new Thread(animation); 
        
        t.start(); 
        
        countText.setText(Integer.toString(animations.size())); 
        
    }
}
