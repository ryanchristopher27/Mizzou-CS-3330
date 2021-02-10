// Name: Ryan Christopher
// Pawprint: rdcb2f
// Date: 1/28/21
// CS 3330
// Challenge 3

package rdcb2flanguagebasicss21;

// Imports
//import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 
//import java.util.Date;
import java.util.Random;

/**
 * @author Ryan Christopher
 * 
 * @references
 *   1) https://stackoverflow.com/questions/3680637/generate-a-random-double-in-a-range
 *   2) https://www.javatpoint.com/java-string-to-int#:~:text=We%20can%20convert%20String%20to,returns%20instance%20of%20Integer%20class.
 *   3) https://stackoverflow.com/questions/2538787/how-to-print-a-float-with-2-decimal-places-in-java
 *   4) https://www.javatpoint.com/java-get-current-date
 */
public class Rdcb2fLanguageBasicsS21 {

    public static void main(String[] args) {
        
        // Code for "hour" from Reference 4
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H");
        LocalDateTime now = LocalDateTime.now();
        // End of code from Reference 4
        
        // Code for "grade"
        Random r = new Random();
        
        
        // Declarations
        char c1 = 'c';
        char c2 = 99;
        short qualityScore = 61;
        float miles = 150f;
        float milesPerGallon = 27.5f;
        float gasPrice = 2.34f;
        boolean sunny = false;
        boolean warm = false;
        int hour = Integer.parseInt(dtf.format(now));   // Code from Reference 2
        double grade = 0 + (4 * r.nextDouble());    // Code from Reference 1
        String greeting = "Hi";
        String myPawPrint = "rdcb2f";
        
        // Display Code
        // Compare c1 and c2
        if (c1 == c2) {
            System.out.println(c1 + " and " + c2 + " are the same.");
        }
        else {
            System.out.println(c1 + " and " + c2 + " are NOT the same.");
        }
        
        // Check qualityScore
        if ((qualityScore >= 0) && (qualityScore <=60)) {
            System.out.println("The quality is bad.");
        }
        else {
            System.out.println("Good quality");
        }
        
        // gasFee code
        float gasFee = (miles / milesPerGallon) * gasPrice;
        System.out.printf("Total gas fee = %.3f\n", gasFee);    // Code from reference 3
        
        // Check if sunny and warm
        if ((sunny == true) && (warm == true)) {
            System.out.println("Go swimming.");
        }
        else if ((sunny == false) && (warm == true)) {
            System.out.println("Go hiking.");
        }
        else {
            System.out.println("Stay home and code.");
        }
        
        // Check time of day
        switch(hour) {
            case 5: case 6: case 7: case 8: case 9: case 10: 
                System.out.println("The current time is " + hour + " in the MORNING.");
                break;
            case 11: case 12: case 13: case 14: case 15: case 16:
                System.out.println("The current time is " + hour + " in the AFTERNOON.");
                break;
            case 17: case 18: case 19: case 20: case 21: case 22:
                System.out.println("The current time is " + hour + " in the EVENING.");
                break;
            case 23: case 0: case 1: case 2: case 3: case 4:
                System.out.println("The current time is " + hour + " in the NIGHT.");
                break;
            default:
                System.out.println("You have the wrong time.");
                break;
        }
        
        // Check GPA
        if ((grade >= 0) && (grade < 0.7)) {
            System.out.println("The student's GPA grade is an F in the class.");
        }
        else if ((grade >= 0.7) && (grade < 1.70)) {
            System.out.println("The student's GPA grade is a D- to D+ in the class.");
        }
        else if ((grade >= 1.7) && (grade < 2.7)) {
            System.out.println("The student's GPA grade is a C- to C+ in the class.");
        }
        else if ((grade >= 2.7) && (grade < 3.7)) {
            System.out.println("The student's GPA grade is a B- to B+ in the class.");
        }
        else {
            System.out.println("The student's GPA grade is an A- to A+ in the class.");
        }
        
        // Counter code
        for (int count = 2; count < 11; count++) {
            if (count%3 == 0) {
                System.out.println("Count: " + count);
            }
        }
        
        // Count Down Code
        int countDown = 10;
        while (countDown > 0) {
            System.out.println("Count Down: " + countDown);
            countDown--;
            if (countDown == 0) {
                System.out.println("Houston, we have a lift off!");
            }
        }
        
        // Invoke Me Call
        InvokeMe(greeting, myPawPrint);
        
    }
    
    // Invoke Me Method
    public static void InvokeMe(String greeting, String myPawPrint) {
        System.out.printf(greeting + ", my pawprint is " + myPawPrint + " and today's date is ");
      
        // Start of code for Referece 4
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mma");  
        LocalDateTime now = LocalDateTime.now();  
        System.out.println(dtf.format(now)); 
        // End of code for Reference 4
    }
    
}
