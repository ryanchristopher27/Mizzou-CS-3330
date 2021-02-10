/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginform;

import javafx.scene.control.Label;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author rdash
 */
public class LoginForm extends Application {
    
    private String title = "My JavaFX Login";
    private int width = 600;
    private int height = 400;
    
    private String loginTitle = "Welcome Please Login";
    private String fontStyle = "Comic Sans MS";
    
    @Override
    public void start(Stage primaryStage) {
        
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(20);
        root.setVgap(20);
        root.setPadding(new Insets(25, 25, 25, 25));
        
        Text sceneTitle = new Text(loginTitle);
        sceneTitle.setFont(Font.font(fontStyle, FontWeight.BOLD, 26));
        root.add(sceneTitle, 0, 0, 2, 1);
        
        Label userNameLabel = new Label("Username: ");
        userNameLabel.setFont(Font.font(fontStyle, FontWeight.NORMAL, 18));
        root.add(userNameLabel, 0, 1);
        
        TextField userNameTextField = new TextField();
        root.add(userNameTextField, 1, 1);
        
        Label passwordLabel = new Label("Password: ");
        passwordLabel.setFont(Font.font(fontStyle, FontWeight.NORMAL, 18));
        root.add(passwordLabel, 0, 2);
        
        PasswordField passwordField = new PasswordField();
        root.add(passwordField, 1, 2);
        
        // Makes Grid Lines Visible
//        root.setGridLinesVisible(true);
        
        // Code for button
        Button loginButton = new Button("Sign In");
        HBox hboxForButton = new HBox(10);
        hboxForButton.setAlignment(Pos.BOTTOM_RIGHT);
        hboxForButton.getChildren().add(loginButton);
        root.add(hboxForButton, 1, 4);
        
        Text actionTarget = new Text();
        GridPane.setColumnIndex(actionTarget, 1);
        GridPane.setRowIndex(actionTarget, 6);
        root.getChildren().add(actionTarget);
        
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                
                if (userNameTextField.getText().length() > 5 && passwordField.getText().length() > 5) {
                    actionTarget.setFill(Color.FIREBRICK);
                    actionTarget.setText("Sign In Button Pressed"); // Use on challenge
                
                    System.out.println("username: " + userNameTextField.getText()); // Use on challenge
                    System.out.println("password: " + passwordField.getText()); // Use on challenge
                }
                else {
                    System.out.println("ERROR: Username or Password must be 5 characters");
                }
            }
        });
        
        
        Scene scene = new Scene(root, width, height);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle(title);
        primaryStage.show();
        
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
