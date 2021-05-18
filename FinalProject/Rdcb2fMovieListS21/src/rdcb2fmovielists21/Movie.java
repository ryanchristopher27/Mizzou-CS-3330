/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rdcb2fmovielists21;

/**
 *
 * @author Ryan Christopher
 */
public class Movie implements java.io.Serializable {
    
    public String title;
    public String genre;
    public int rating;
    
    // Movie Class Constructors
    public Movie() {
        title = "None";
        genre = "None";
        rating = 0;
    }
    public Movie(String title) {
        this.title = title;
    }
    public Movie(String title, String genre) {
        this(title);
        
        this.genre = genre;
    }
    public Movie(String title, String genre, int rating) {
        this(title, genre);
        
        this.rating = rating;
    }
    
    // Movie Class Setters
    public void setTitle(String title) {
        this.title = title;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    
    // Movie Class Getters
    public String getTitle() {
        return title;
    }
    public String getGenre() {
        return genre;
    }
    public int getRating() {
        return rating;
    }
    
}
