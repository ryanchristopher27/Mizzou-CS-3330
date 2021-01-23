package rdcb2fhelloworlds21;

// Imports for Reference 1
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  

// Imports for Reference 2
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * @author Ryan Christopher
 * 
 * @references
 *      1) https://www.javatpoint.com/java-get-current-date
 *      2) https://beginnersbook.com/2017/10/java-date-format-to-display-the-day-of-the-week/
 */
public class Rdcb2fHelloWorldS21 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int myCourseNum = 3330;
        
        System.out.println("Hello World!");               
        InvokeMe(myCourseNum);
    }
    
    public static void InvokeMe(int num) {
        System.out.println("My course number is " + num);
      
        // Start of code for Referece 1
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mma");  
        LocalDateTime now = LocalDateTime.now();  
        System.out.println("Today's date is " + dtf.format(now)); 
        // End of code for Reference 1
        
        // Start of code for Reference 2
        SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE");
	String stringDate2 = sdf2.format(new Date());
	System.out.println("Today is: "+stringDate2);
        // End of code for Reference 2
    }
}
