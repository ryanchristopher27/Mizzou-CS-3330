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
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Ryan Christopher
 */
public class FXMLMovieSelectorController extends Switchable implements Initializable, PropertyChangeListener, Switcher {
    
    @FXML
    private ChoiceBox<String> selectedGenreInput;
    @FXML
    private Text selectedMovieTitle;
    @FXML
    private Text selectedMovieGenre;
    @FXML
    private Text selectedMovieRating;
    
    MovieListModel movieListModel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        movieListModel = new MovieListModel();        
        movieListModel.addPropertyChangeListener(this);
        
        selectedMovieTitle.setText("");
        selectedMovieGenre.setText("");
        selectedMovieRating.setText("");
        
        selectedGenreInput.setItems(FXCollections.observableArrayList(
            "All","Action", "Comedy", "Drama", "Fantasy", "Horror", "Mystery", "Romance", "Thriller",
                    "Documentary", "Animated", "Adventure", "Sci-Fi")
        );
        
        selectedGenreInput.setValue("All");
    }    

    @FXML
    @Override
    public void goToA(ActionEvent event) {
        Switchable.switchTo("FXMLMovieList");
    }

    @FXML
    @Override
    public void goToB(ActionEvent event) {
        Switchable.switchTo("FXMLAboutMe");
    }

    @FXML
    private void selectGenre(ActionEvent event) {
        movieListModel.getRandomMovie(selectedGenreInput.getValue());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("RandomMovieTitle")) {
            selectedMovieTitle.setText(evt.getNewValue().toString());
        }
        else if (evt.getPropertyName().equals("RandomMovieGenre")) {
            selectedMovieGenre.setText(evt.getNewValue().toString());
        }
        else if (evt.getPropertyName().equals("RandomMovieRating")) {
            selectedMovieRating.setText(evt.getNewValue().toString());
        }
        else if (evt.getPropertyName().equals("NoRandomMovie")) {
            selectedMovieTitle.setText("No " + evt.getNewValue().toString() + " Movies");
            selectedMovieGenre.setText("");
            selectedMovieRating.setText("");
        }
    }
}
