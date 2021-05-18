/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadedunzipper;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Professor Wergeles
 * 
 * @references
 *      1)  http://stackoverflow.com/questions/9324933/what-is-a-good-java-library-to-zip-unzip-files
 *      2)  http://docs.oracle.com/javase/7/docs/api/java/io/FileFilter.html
 *      3)  http://javadox.com/net.lingala.zip4j/zip4j/1.3.1/net/lingala/zip4j/core/ZipFile.html#extractAll(java.lang.String)
 *      4)  https://docs.oracle.com/javase/8/docs/api/java/lang/FunctionalInterface.html
 *      5)  http://www.lingala.net/zip4j/
 */
public class UserInterfaceController implements Initializable {

    private String appName = "Threaded Unzipper";
    private Stage stage;
    
    @FXML
    private TextField sourceDirectoryTextField;
    
    @FXML
    private TextField destinationDirectoryTextField;
    
    @FXML
    private ProgressBar progressBar;
    
    @FXML
    private TextArea statusTextArea;
    
    @FXML
    private Button handleUnzipButton;
    
    private File sourceDirectory;
    private File destinationDirectory;
    private Unzip unzip;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void ready(Stage stage) {
        this.stage = stage;
        stage.setTitle(appName);
        
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent we){
                if(unzip != null){
                    unzip.end(); 
                }
            }
        });
    }
    
    @FXML
    private void handleSelectSourceDirectory(Event event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File directory = directoryChooser.showDialog(stage);
        if (directory != null) {
            sourceDirectory = directory;
            sourceDirectoryTextField.setText(directory.getPath());
        }
    }
    
    @FXML
    private void handleSelectDestinationDirectory(Event event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File directory = directoryChooser.showDialog(stage);
        if (directory != null) {
            destinationDirectory = directory;
            destinationDirectoryTextField.setText(directory.getPath());
        }        
    } 
    
    @FXML
    private void handleUnzip(Event event) {
        if(unzip != null){
            unzip.end(); 
            return; 
        }
        
        progressBar.setProgress(0);
        statusTextArea.clear();
        
        if (sourceDirectory == null) {
            statusTextArea.appendText("The source directory is not set.\n");
            return;
        }
        if (destinationDirectory == null) {
            statusTextArea.appendText("The destination directory is not set.\n");
            return;
        }
         
        handleUnzipButton.setText("Stop");
        
        unzip = new Unzip(sourceDirectory, destinationDirectory); 
        
        unzip.setOnNotifiction(new Notification() {
            @Override
            public void handle(double percentComplete, UnzipState state, String message){
                progressBar.setProgress(percentComplete);
                statusTextArea.appendText(message + "\n"); 
                
                if(state != UnzipState.RUNNING){
                    unzip = null; 
                    handleUnzipButton.setText("Unzip");
                }
            }
        });
        
        statusTextArea.appendText("Number of file to unzip: " + unzip.getNumFiles() + "\n"); 
        
        unzip.start(); 
    }
}