package org.brianodisho.twitter.production;
 
import java.util.Scanner;
import org.brianodisho.twitter.connection.Connection;
import org.brianodisho.twitter.search.Timeline;
import org.brianodisho.twitter.sql.Database;

/**
 *
 * @author brianodisho
 */
 
public class Main {
 
    public static void main(String[] args) {
 
        // Store OAuth credentials received from Twitter to variables
        String consumerKey= "*********************";
        String consumerSecret= "*********************";
        String accessToken= "*********************";
        String accessTokenSecret= "*********************";
                
        // Attempt to connect to Twitter with OAuth credentials
        Connection connection = new Connection(consumerKey, consumerSecret, accessToken, accessTokenSecret);
        
        // Instantiate Timeline object
        Timeline userTimeline = new Timeline(connection.getConnection());
        
        Scanner input = new Scanner(System.in);
        System.out.print("Enter screen name...\t ");
        
        // Search for a user's recent tweets by screen name
        userTimeline.setUser(input.nextLine());
        userTimeline.printUser();
        
        System.out.println("Press [Enter] to view the user's recent timeline...");
        input.nextLine();
        
        userTimeline.refreshTimeline();
        userTimeline.printTimeline();

        System.out.println("\nPress [Enter] to store the timeline to the database...");
        input.nextLine();
        
        Database database = new Database(
                "jdbc:mysql://localhost:3306/twitter", 
                "javaTwitterSearch", 
                "password");
        
        // Enter user into database
        database.executeUserUpdate(userTimeline.getUser());
        database.executeTweetUpdate(userTimeline.getTimeline());
        
    }
 
}