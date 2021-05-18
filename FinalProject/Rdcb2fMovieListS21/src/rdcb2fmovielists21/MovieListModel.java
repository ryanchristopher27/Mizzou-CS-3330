/*
 * Name: Ryan Christopher
 * Pawprint: rdcb2f
 * Class: CS 3330 Object Oriented Programming
 * Final Project: Movie List
 */
package rdcb2fmovielists21;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.regex.PatternSyntaxException;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Ryan Christopher
 * 
 * @references
 * 1) Code from serialization lecture
 * 2) Code from scene switching lecture
 */
public class MovieListModel extends AbstractModel {
    
    ArrayList<Movie> allMovies = new ArrayList<>();
    public static int movieCount = 0;
    private String fileName = "saveFile.ser";
    
    public MovieListModel() {
        
        fileOpen();
        if (allMovies != null) {
            movieCount = allMovies.size();
        }
    }
    
    public void updateMovieList(String genreSelection) {
        clearMovieList();
    
        String formattedString = "";
        for (int i = 0; i < movieCount; i++) {
            if (genreSelection.equals(allMovies.get(i).genre) || genreSelection.equals("All")) {
                formattedString = formatMovieInputString(allMovies.get(i));
                firePropertyChange("UpdateMovieList", null, formattedString);
            }
        }
    }
    
    public void sortMoviesByTitle() {
        Movie tempMovie = new Movie();

        for (int i = 0; i < movieCount; i++) {
            for (int j = 0; j < movieCount - 1; j++) {
                if (allMovies.get(j).title.compareTo(allMovies.get(j+1).title) > 0) {
                    tempMovie = allMovies.get(j);
                    allMovies.set(j, allMovies.get(j+1));
                    allMovies.set(j+1, tempMovie);
                }
            }
        }
    }
    
    public ArrayList<Movie> getAllMovies() {
        return allMovies;
    }
    
    public void getNewMovie(String title, String genre, int rating) {
        Movie movie = new Movie();

        if (title.equals("")) {
            showAlert("Must enter a Movie Title");
        }
        else if (genre.equals("Select Genre")) {
            showAlert("Must enter a Movie Genre");
        }
        else {
            movie.setTitle(title);
            movie.setGenre(genre);
            movie.setRating(rating);

            allMovies.add(movie);
            movieCount++;
            
            fileSave();
            
            sortMoviesByTitle();
            updateMovieList("All");
            clearMovieInputs();
        }
    }
    
    public int getMovieCount() {
        return movieCount;
    }
    
        // Reference 1
    public void fileOpen() {        
        File file = new File(fileName);
        
        FileInputStream fileIn;
        
        try {
            fileIn = new FileInputStream(file.getPath());
            ObjectInputStream in = new ObjectInputStream(fileIn);

            allMovies = (ArrayList) in.readObject();
            
            in.close();
            fileIn.close();            
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException");
        } catch (IOException ex) {
            System.out.println("IOEXception");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException");
        }
    }
    
        // Reference 1
    public void fileSave() {
        if (allMovies == null) {
            return;
        }
        
        FileOutputStream fileOut;
        
        try {
            fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            
            out.writeObject(allMovies);
            out.close();
            fileOut.close();
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException");
        } catch (IOException ex) {
            System.out.println("IOException");
        }
    }
    
        // Reference 2
    private boolean confirmContinueOnDeletion(String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Movie");
        alert.setHeaderText("Delete " + title);
        alert.setContentText("Are you sure you would like to delete this movie?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        }
        else {
            return false;
        }
    }
    
        // Reference 1
    public void showAlert(String contentText) {
        Alert mainAlert = new Alert(Alert.AlertType.ERROR);
        mainAlert.setContentText(contentText);
        mainAlert.show();
    }
    
    public String formatMovieInputString(Movie movie) {
        String formattedString = String.format("Title: %-35.20sGenre: %-20.10sRating: %-2d", movie.title, movie.genre, movie.rating);
        
        return formattedString;
    }
    
    public void clearMovieInputs() {
        firePropertyChange("ClearMovieInputs", null, null);
    }
    
    public void deleteMovieOnDoubleClick(String selectedItem, String genre) {
        for (int i = 0; i < movieCount; i++) {
            if (selectedItem.contains(allMovies.get(i).title)) {
                if (confirmContinueOnDeletion(allMovies.get(i).title)) {
                    allMovies.remove(i);
                    movieCount--;
                }
            }

            updateMovieList(genre);
            fileSave();
        }
    }
    
    private void clearMovieList() {
        firePropertyChange("ClearMovieList", null, null);
    }

    // Code that applies to movie selector
    protected void getRandomMovie(String genre) {
        ArrayList<Movie> newMovies = new ArrayList<>();
        int count = 0;
        
        for (int i = 0; i < movieCount; i++) {
            if (genre.equals(allMovies.get(i).genre) || genre.equals("All")) {
                newMovies.add(allMovies.get(i));
                count++;
            }
        }
        
        if (count > 0) {
            int randomNum = getRandomNumber(count);
            firePropertyChange("RandomMovieTitle", null, newMovies.get(randomNum).title);
            firePropertyChange("RandomMovieGenre", null, newMovies.get(randomNum).genre);
            firePropertyChange("RandomMovieRating", null, newMovies.get(randomNum).rating);
        }
        else {
            firePropertyChange("NoRandomMovie", null, genre);
        }
    }
    
    private int getRandomNumber(int length) {
        Random random = new Random();
        int randomNum = random.nextInt(length);
        return randomNum;
    }
}
