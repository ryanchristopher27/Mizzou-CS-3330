/*
 * Name: Ryan Christopher
 * Pawprint: rdcb2f
 * Class: CS 3330 Object Oriented Programming
 * Final Project: Movie List
 */
package rdcb2fmovielists21;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Ryan Christopher
 */
public class FXMLMovieListController extends Switchable implements Initializable, PropertyChangeListener, Switcher {

    // FXML Variables
    @FXML
    private TextField movieTitleInput;
    @FXML
    private ChoiceBox<String> movieGenreInput;
    @FXML
    private Slider movieRatingInput;
    @FXML
    private Text movieRatingValue;
    @FXML
    private ChoiceBox<String> selectedGenreInput;
    @FXML
    private ListView<String> movieListDisplay;
    @FXML
    private Button movieSubmitButton;
    @FXML
    private Button genreInputButton;
    
    // Controller Variables
    MovieListModel movieListModel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {     
        movieListModel = new MovieListModel();        
        movieListModel.addPropertyChangeListener(this);
                
        // Set values for Drop Down Menus
        movieGenreInput.setItems(FXCollections.observableArrayList(
            "Select Genre", "Action", "Comedy", "Drama", "Fantasy", "Horror", "Mystery", "Romance", "Thriller",
                    "Documentary", "Animated", "Adventure", "Sci-Fi")
        );
        selectedGenreInput.setItems(FXCollections.observableArrayList(
            "All","Action", "Comedy", "Drama", "Fantasy", "Horror", "Mystery", "Romance", "Thriller",
                    "Documentary", "Animated", "Adventure", "Sci-Fi")
        );
        // Set selected Values for Drop Down Menus
        selectedGenreInput.setValue("All");
        movieGenreInput.setValue("Select Genre");
        
        movieListModel.updateMovieList("All");
        updateButtonsAndSliders();
    }    
    
    @FXML
    @Override
    public void goToA(ActionEvent event) {
        Switchable.switchTo("FXMLAboutMe");
    }

    @FXML
    @Override
    public void goToB(ActionEvent event) {
        Switchable.switchTo("FXMLMovieSelector");
    }
    
    public void updateButtonsAndSliders() {
        // New Movie Button Pressed
        movieSubmitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                movieListModel.getNewMovie(movieTitleInput.getText(), movieGenreInput.getValue(), (int)movieRatingInput.getValue());
            } 
        });
        
        // Update Movie List Button Pressed
        genreInputButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                movieListModel.updateMovieList(selectedGenreInput.getValue());
            } 
        });
        
        // Deletes Movie on Double Click
        movieListDisplay.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    movieListModel.deleteMovieOnDoubleClick(movieListDisplay.getSelectionModel().getSelectedItem(), selectedGenreInput.getValue());
                }
            }
        });
        
        // Updates Slider on Movement
        movieRatingInput.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                movieRatingValue.setText(String.valueOf((int)movieRatingInput.getValue()));
            }
        });
        movieRatingInput.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                movieRatingValue.setText(String.valueOf((int)movieRatingInput.getValue()));
            }
        });
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("UpdateMovieList")) {
            movieListDisplay.getItems().add(evt.getNewValue().toString());
        }
        else if (evt.getPropertyName().equals("ClearMovieInputs")) {
            movieTitleInput.setText("");
            movieGenreInput.setValue("Select Genre");
            movieRatingInput.setValue(0);
            movieRatingValue.setText(String.valueOf((int)movieRatingInput.getValue()));
        }
        else if (evt.getPropertyName().equals("ClearMovieList")) {
            movieListDisplay.getItems().clear();
        }
    }
}
