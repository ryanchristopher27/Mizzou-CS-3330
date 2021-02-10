/*
 * Name: Ryan Christopher
 * Pawprint: rdcb2f
 * Class: CS 3330 - Object Oriented Programming
 * Challenge: MoviesS21
 */
package rdcb2fmoviess21;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Ryan Christopher
 * 
 * @references
 *  1) Code used from online App
 *  2) http://tutorials.jenkov.com/java-internationalization/simpledateformat.html
 *  3) https://kodejava.org/how-do-i-format-a-number-as-currency-string/
 *  3) https://www.javatpoint.com/java-double-to-string
 *  4) https://www.javatpoint.com/java-float-to-string
 *  5) https://www.javatpoint.com/java-get-current-date
 *  6) https://stackoverflow.com/questions/9015536/how-to-add-10-minutes-to-my-string-time
 *  7) https://stackoverflow.com/questions/7374356/need-help-splitting-a-string-into-two-separate-integers-for-processing
 *  8) https://stackoverflow.com/questions/43263680/removing-all-characters-but-letters-in-a-string
 *  
 * 
 */
public class Rdcb2fMoviesS21 {

    public static void main(String[] args) {
        // General
        Calendar cal = Calendar.getInstance(); // Reference 1
        
        // Movie 1 
        Movie movie1 = new Movie("Soul", "Pete Doctor and Kemp Powers", "1h40m");
        movie1.setGenre(Genre.ANIMATION);
        movie1.setSummary("After landing the gig of a lifetime, a New York jazz "
                + "pianist suddenly finds himself trapped in a strange land between Earth and the afterlife.");
        movie1.setRating(8.1f);
        movie1.setRevenue(82700000);
        cal.set(2020, 12, 25); // Reference 1
        movie1.setReleaseDate(cal.getTime()); // Reference 1
        
        // Movie 2
        cal.set(2017, 6, 21); // Reference 1
        Movie movie2 = new Movie("Transformers: The Last Knight", "Michael Bay", "A deadly threat "
                + "from Earth's history reappears and a hunt for a lost artifact takes place between "
                + "Autobots and Decepticons, while Optimus Prime encounters his creator in "
                +  "space.", 5.2f, 602800000, Genre.ACTION, cal.getTime(), "2h34m");
        
        // Movie 3
        cal.set(1994, 7, 6); // Reference 1
        Movie movie3 = new Movie("Forrest Gump", "Robert Zemeckis", "The presidencies "
                + "of Kennedy and Johnson, the events of Vietnam, Watergate and other "
                + "historical events unfold through the perspective of an Alabama man "
                + "with an IQ of 75, whose only desire is to be reunited with his childhood "
                + "sweetheart.", Genre.DRAMA, cal.getTime(), "2h22m");
        movie3.setRating(8.8f);
        movie3.setRevenue(679800000);
        
        // Movie 4
        Movie movie4 = new Movie();
        movie4.setName("The Godfather");
        movie4.setDirector("Francis Ford Coppola");
        movie4.setSummary("An organized crime dynasty's aging patriarch transfers "
                + "control of his clandestine empire to his reluctant son.");
        movie4.setGenre(Genre.DRAMA);
        movie4.setRating(9.2f);
        movie4.setRevenue(287258196);
        cal.set(1972, 3, 27); // Reference 1
        movie4.setReleaseDate(cal.getTime()); // Reference 1
        movie4.setRuntime("2h55m");
        
        // Start of code from Reference 2
        String releaseDatePattern = "MMM dd, yyyy";
        SimpleDateFormat releaseDateFormat = new SimpleDateFormat(releaseDatePattern);
        // End of code from Reference 2
        
        // Printing Movie 1
        System.out.println("MOVIE 1:");
        System.out.println("Name: " + movie1.getName());
        System.out.println("Director: " + movie1.getDirector());
        System.out.println("Summary: " + movie1.getSummary());
        System.out.println("Genre: " + movie1.getGenre());
        System.out.println("Rating: " + movie1.getRating());
        System.out.println("Revenue: " + movie1.getRevenue());
        System.out.println("Release Date: " + releaseDateFormat.format(movie1.getReleaseDate()));
        System.out.println("Runtime: " + movie1.getRuntime());
        System.out.println("Version: " + movie1.getVersion());
        movie1.playMovie();
        System.out.println("\n");
        
        // Print Movie 2
        System.out.println("MOVIE 2:");
        System.out.println("Name: " + movie2.getName());
        System.out.println("Director: " + movie2.getDirector());
        System.out.println("Summary: " + movie2.getSummary());
        System.out.println("Genre: " + movie2.getGenre());
        System.out.println("Rating: " + movie2.getRating());
        System.out.println("Revenue: " + movie2.getRevenue());
        System.out.println("Release Date: " + releaseDateFormat.format(movie2.getReleaseDate()));
        System.out.println("Runtime: " + movie2.getRuntime());
        System.out.println("Version: " + movie2.getVersion());
        movie2.playMovie();
        System.out.println("\n");
        
        // Print Movie 3
        System.out.println("MOVIE 3:");
        movie3.print();
        System.out.println("\n");
        
        // Print Movie 4
        System.out.println("MOVIE 4:");
        movie4.print();
        System.out.println("\n");
        
        // Print Number of Movies
        System.out.println("Number of Movies: " + movie1.numOfMovies);
        
    }
    
}
