/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rdcb2fgpacalculators21;

// Imports
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * @author Ryan Christopher
 * 
 * @references
 *  1) 
 */
public class Rdcb2fGPACalculatorS21 extends Application {
    
    private String title = "GPA Calculator";
    private int width = 500;
    private int height = 425;
    
    private String fontStyle = "Times New Roman";
    
    @Override
    public void start(Stage primaryStage) {
        
        GridPane root = new GridPane();
        root.setAlignment(Pos.BASELINE_CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(15, 15, 15, 15));
        
        // Create and Add Labels
        Label courseOneLabel = new Label("Course 1:");
        courseOneLabel.setFont(Font.font(fontStyle, FontWeight.NORMAL, 18));
        root.add(courseOneLabel, 0, 0, 1, 1);
        
        Label courseTwoLabel = new Label("Course 2:");
        courseTwoLabel.setFont(Font.font(fontStyle, FontWeight.NORMAL, 18));
        root.add(courseTwoLabel, 0, 1, 1, 1);
        
        Label courseThreeLabel = new Label("Course 3:");
        courseThreeLabel.setFont(Font.font(fontStyle, FontWeight.NORMAL, 18));
        root.add(courseThreeLabel, 0, 2, 1, 1);
        
        Label courseFourLabel = new Label("Course 4:");
        courseFourLabel.setFont(Font.font(fontStyle, FontWeight.NORMAL, 18));
        root.add(courseFourLabel, 0, 3, 1, 1);
        
        Label informationLabel = new Label("Information:");
        informationLabel.setFont(Font.font(fontStyle, FontWeight.NORMAL, 18));
        root.add(informationLabel, 0, 4, 2, 1);
                
         
        
        // Create and Add Text Fields
        TextField score1 = new TextField();
        score1.setPrefWidth(350);
        root.add(score1, 1, 0, 1, 1);
        
        TextField score2 = new TextField();
        score2.setPrefWidth(350);
        root.add(score2, 1, 1, 1, 1);
        
        TextField score3 = new TextField();
        score3.setPrefWidth(350);
        root.add(score3, 1, 2, 1, 1);
        
        TextField score4 = new TextField();
        score4.setPrefWidth(350);
        root.add(score4, 1, 3, 1, 1);
        
        // Create Text Area
        TextArea infoArea = new TextArea();
        infoArea.setPrefRowCount(3);
        infoArea.setWrapText(true);
        infoArea.setEditable(false);
        root.add(infoArea, 0, 5, 2, 1);
        
        
        // Create Buttons
        Button calculateAverageScoreButton = new Button("Calculate Average Score");
        calculateAverageScoreButton.setMaxWidth(Double.MAX_VALUE);
        
        Button calculateGPAButton = new Button("Calculate GPA");
        calculateGPAButton.setMaxWidth(Double.MAX_VALUE);
        
        Button alertButton = new Button("Alert");
        alertButton.setMaxWidth(Double.MAX_VALUE);
        
        Button clearAllButton = new Button ("Clear All");
        clearAllButton.setMaxWidth(Double.MAX_VALUE);
        
        
        // Create VBox for Buttons
        VBox vboxForButtons = new VBox(10);
        vboxForButtons.setAlignment(Pos.CENTER);
        vboxForButtons.getChildren().add(calculateAverageScoreButton);
        vboxForButtons.getChildren().add(calculateGPAButton);
        vboxForButtons.getChildren().add(alertButton);
        vboxForButtons.getChildren().add(clearAllButton);
        root.add(vboxForButtons, 0, 6, 2, 1);
        
        
        // Create Scene
        Scene scene = new Scene(root, width, height);
        
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
