package org.brianodisho.twitter.connection;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

/**
 * The following class sets OAuth credentials using ConfigurationBuilder and
 * instantiates a Twitter instance bound to those credentials.
 *
 * @author brianodisho
 */
public class Connection {

    private final Twitter twitter;

    // Constructor sets OAuth credentials using the ConfigurationBuilder class included with Twitter4J
    public Connection(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret) {

        // Instantiate a new ConfigurationBuilder object & set OAuth credentials
        ConfigurationBuilder config = new ConfigurationBuilder();
        config.setDebugEnabled(false)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);

        // Instantiate re-usable factory with the configuration provided by ConfigurationBuilder
        TwitterFactory twitterFactory = new TwitterFactory(config.build());

        // Instantiate a new twitter instance using the factory
        twitter = twitterFactory.getInstance();

        System.out.println("[CONNECTION ESTABLISHED]");

    }

    // Returns the twitter instance created by the factory
    public Twitter getConnection() {

        return twitter;

    }

}
