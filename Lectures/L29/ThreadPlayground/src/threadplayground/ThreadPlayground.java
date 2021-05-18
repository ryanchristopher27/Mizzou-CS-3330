/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadplayground;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Professor Wergeles
 * 
 * @note This is a mostly empty project that is the starting point for playing with threads.
 *          Not a lot has been implemented here yet.  It gives us a place to experiment.
 *          The thread code is written in UserInterfaceController.java
 */
public class ThreadPlayground extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("UserInterface.fxml"));
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserInterface.fxml"));
        Parent root = (Parent)loader.load();
        UserInterfaceController controller = (UserInterfaceController)loader.getController();
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        
        controller.ready(stage);
        
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
