// Movie Class File

package rdcb2fmoviess21;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @author Ryan Christopher
 * 
 * @references Included in Rdcb2fMoviesS21.java
 */
public class Movie {
    private String name;
    private String director;
    private String summary;
    private float rating = 0.0f;
    private double revenue = 0.0;
    private Genre genre;
    
    private final Calendar myCalendar = new GregorianCalendar(1888, 9, 14); // Code from TA Office Hours with Xin Cui
    private Date releaseDate = myCalendar.getTime();
    private String runtime;
    private int version;
    
    public static int numOfMovies = 0;
    
    public Movie() {
        name = "";
        director = "";
        version = 0;
        numOfMovies++;
    }
    
    public Movie(String name, String director, String runtime) {
        this();
        this.name = name;
        this.director = director;
        this.runtime = runtime;
    }
    
    public Movie(String name, String director, String summary, Genre genre, Date releaseDate, String runtime) {
        this(name, director, runtime);
        this.summary = summary;
        this.genre = genre;
        this.releaseDate = releaseDate;
        version = 1;
    }
    
    public Movie(String name, String director, String summary, float rating, double revenue, Genre genre, Date releaseDate, String runtime) {
        this(name, director, summary, genre, releaseDate, runtime);
        this.rating = rating;
        this.revenue = revenue;
    }
    
    public void setName(String name) {
        this.name = name;
        incrementVersion();
    }
    
    public void setDirector(String director) {
        this.director = director;
    }
    
    public void setRating(float rating) {
        this.rating = rating;
        incrementVersion();
    }
    
    public void setRevenue(double revenue) {
        this.revenue = revenue;
        incrementVersion();
    }
    
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
        incrementVersion();
    }
    
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    
    public void setSummary(String summary) {
        this.summary = summary;
        incrementVersion();
    }
    
    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getDirector() {
        return this.director;
    }
    
    public String getRating() {
        
        return Float.toString(this.rating); // Code from Reference 4
    }
    
    public String getRevenue() {
        NumberFormat revenueFormat = NumberFormat.getCurrencyInstance(Locale.US); // Code from Reference 3
        return revenueFormat.format(revenue); // Code from Reference 3
    }
    
    public Date getReleaseDate() {
        return this.releaseDate;
    }
    
    public Genre getGenre() {
        return this.genre;
    }
    
    public String getSummary() {
        return this.summary;
    }
    
    public String getVersion() {
        return Integer.toString(this.version); // Code from Reference 4
    }
    
    public String getRuntime() {
        return this.runtime;
    }
    
    public void playMovie()  {
        // Start of Code from Reference 5
        DateTimeFormatter playMovieFormatter = DateTimeFormatter.ofPattern("MM-d-yyyy h:mm a");
        LocalDateTime now = LocalDateTime.now();
        // End of Code from Reference 5
        
        String numbers [] = this.runtime.split("h"); // Code from Reference 7
        numbers[1] = numbers[1].replaceAll("m", ""); // Code from Reference 8
        
        // Start of Code from Reference 6
        now = now.plusHours(Integer.valueOf(numbers[0]));
        now = now.plusMinutes(Integer.valueOf(numbers[1]));
        // End of Code from Reference 6
        
        System.out.println("The runtime of " + this.name + " is " + this.runtime);
        System.out.println(this.name + " will end at " + playMovieFormatter.format(now));
    }
    
    public void incrementVersion() {
        version++;
    }
    
    public void print() {
        // Start of code from Reference 2
        String releaseDatePattern = "MMM d, yyyy";
        SimpleDateFormat releaseDateFormat = new SimpleDateFormat(releaseDatePattern);
        // End of code from Reference 2
        
        System.out.println("Name: " + this.getName());
        System.out.println("Director: " + this.getDirector());
        System.out.println("Summary: " + this.getSummary());
        System.out.println("Genre: " + this.getGenre());
        System.out.println("Rating: " + this.getRating());
        System.out.println("Revenue: " + this.getRevenue());
        System.out.println("Release Date: " + releaseDateFormat.format(this.getReleaseDate()));
        System.out.println("Runtime: " + this.getRuntime());
        System.out.println("Version: " + this.getVersion());
        this.playMovie();
    }
}


