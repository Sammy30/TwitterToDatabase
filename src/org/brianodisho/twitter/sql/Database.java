package org.brianodisho.twitter.sql;

import java.sql.*;
import twitter4j.*;

/**
 *
 * @author brianodisho
 */
public class Database {

    private Connection connection;
    private PreparedStatement prepStatement;

    // Constructor loads drivers for DriverManager and establishes connection to the DB
    public Database(String server, String user, String pw) {

        try {
            // Specify to the DriverManager which drivers to load
            Class.forName("com.mysql.jdbc.Driver");

            // Obtain connection instance from DriverManager
            connection = DriverManager.getConnection(server, user, pw);

        } catch (SQLException ex) {
            System.err.printf("[!SQL EXCEPTION : %s]%n", ex);
        } catch (ClassNotFoundException ex) {
            System.err.printf("[!CLASS NOT FOUND EXCEPTION : %s]%n", ex);
        }

        System.out.printf("[CONNECTION TO %s ESTABLISHED]%n", server);

    }

    // Closes the current connection to the database
    public void closeConnection() {

        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.printf("[!SQL EXCEPTION : %s]%n", ex);
        }

        System.out.println("[CONNECTION TO SQL DATABASE CLOSED]");

    }

    // Inserts new row in twitter.tweet table
    public void executeTweetUpdate(ResponseList<Status> responseList) {

        int newRow = 0;
        String query
                = "INSERT INTO twitter.tweet (tweetID, tweetUserID, createdAt, tweetText, url, "
                + "favoriteCount, retweetCount) values (?, ?, ?, ?, ?, ?, ?)";
        String url;

        try {
            
            prepStatement = connection.prepareStatement(query);

            for (Status status : responseList) {
            
                url = "N/A";
                
                // Check tweet for urls
                if (status.getURLEntities().length > 0) { 
                    // Pull urls out of status
                    for (URLEntity urlEnt : status.getURLEntities()) {
                        url = urlEnt.getExpandedURL();
                    }
                }
                
                prepStatement.setLong(1, status.getId());
                prepStatement.setLong(2, status.getUser().getId());
                prepStatement.setDate(3, new java.sql.Date(status.getCreatedAt().getTime()));
                prepStatement.setString(4, status.getText().replaceAll("\n", " "));
                prepStatement.setString(5, url);
                prepStatement.setInt(6, status.getFavoriteCount());
                prepStatement.setInt(7, status.getRetweetCount());
                prepStatement.executeUpdate();

                newRow++;
            }

        } catch (SQLException ex) {
            System.err.printf("[!SQL EXCEPTION : %s]%n", ex);
        }

        System.out.printf("[%d NEW ROWS ADDED TO \"twitter.tweet\" TABLE]%n", newRow);

    }

    // Inserts new row in twitter.user table
    public void executeUserUpdate(User user) {

        String userQuery
                = "INSERT INTO twitter.user (userID, createdAt, screenName, followers, "
                + "following, verified) values (?, ?, ?, ?, ?, ?)";

        try {

            prepStatement = connection.prepareStatement(userQuery);
            prepStatement.setLong(1, user.getId());
            prepStatement.setDate(2, new java.sql.Date(user.getCreatedAt().getTime()));
            prepStatement.setString(3, user.getScreenName());
            prepStatement.setInt(4, user.getFollowersCount());
            prepStatement.setInt(5, user.getFriendsCount());
            prepStatement.setBoolean(6, user.isVerified());
            prepStatement.executeUpdate();

        } catch (SQLException ex) {
            System.err.printf("[!SQL EXCEPTION : %s]%n", ex);
        }

    }

}
