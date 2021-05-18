/*
 * Name: Ryan Christopher
 * Pawprint: rdcb2f
 * Class: CS 3330 Object Oriented Programming
 * Final Project: Movie List Main
 */
package rdcb2fmovielists21;

import javafx.application.Application;
import static javafx.application.Application.STYLESHEET_MODENA;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Ryan Christopher
 * 
 * @references
 *  1) Code from Switch Scenes Lecture
 */
public class Rdcb2fMovieListS21 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {  
            // Reference 1
        HBox root = new HBox();
        root.setPrefSize(600, 400);
        root.setAlignment(Pos.CENTER);
        Text message = new Text("This is a failure!");
        message.setFont(Font.font(STYLESHEET_MODENA, 32));
        root.getChildren().add(message);

        Scene scene = new Scene(root);
        
        Switchable.scene = scene;
        Switchable.switchTo("FXMLMovieList");
        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
