package org.brianodisho.twitter.search;

import twitter4j.*;

/**
 * The class stores statuses from the requested user to a List before outputting
 * the result.
 *
 * @author brianodisho
 */
public class Timeline {

    private final Twitter twitter;
    private User user;
    private ResponseList<Status> timeline;

    // Constructor
    public Timeline(Twitter twitter) {
        this.twitter = twitter;
    }

    // Set User object to twitter screenName
    public void setUser(String screenName) {

        try {
            user = twitter.users().showUser(screenName);
        } catch (TwitterException ex) {
            System.err.println("[TWITTER EXCEPTION]" + ex.getErrorMessage());
            user = null;
        }

    }

    // Returns the User object
    public User getUser() {
        return user;
    }

    // Assigns the timeline from the requested user to ResponseList<>
    public void setTimeline() {

        try {
            timeline = (twitter.getUserTimeline(user.getId(), new Paging(1, 100)));
        } catch (TwitterException ex) {
            System.err.println("[TWITTER EXCEPTION]" + ex.getErrorMessage());
        }

    }

    // Returns the user timeline
    public ResponseList<Status> getTimeline() {

        return timeline;

    }

    // Prints user info to cmd line
    public void printUser() {

        System.out.println("---------------------------");
        System.out.println("User ID     : " + user.getId());
        System.out.println("User Since  : " + user.getCreatedAt());
        System.out.println("User Name   : " + user.getName());
        System.out.println("Screen Name : " + user.getScreenName());
        System.out.println("Followers   : " + user.getFollowersCount());
        System.out.println("Following   : " + user.getFriendsCount());
        System.out.println("Verified    : " + user.isVerified());
        System.out.println("---------------------------\n");

    }

    // Iterates through timeline prints status to cmd line
    public void printTimeline() {

        for (Status status : timeline) {
            System.out.print(timeline.indexOf(status) + 1 + ". ");
            System.out.println(status.getText().replaceAll("\n", " "));
        }

    }

}
