/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvcstopwatch;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Professor Wergeles <Professor Wergeles at cs3330@missouri.edu>
 */
public class MVCStopwatch extends Application {
    
    private String appName = "MVC MyNewStopwatch";
    
    @Override
    public void start(Stage primaryStage) {

        StopwatchController stopwatchController = new StopwatchController();
        
        Scene scene = new Scene(stopwatchController.getRootContainer(), 
                                stopwatchController.getWidth(), 
                                stopwatchController.getHeight());
        
        primaryStage.setTitle(appName);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        stopwatchController.start(); 
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
