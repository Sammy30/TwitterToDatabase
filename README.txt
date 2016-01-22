Twitter To Database


USE CASE

1. The program user provides the screen name of a Twitter user. Twitter returns information about the screen name and the last 200 statuses posted by the screen name. Information about the screen name is stored in a MySQL database named "Twitter" in a table named "user" if a record for the screen name doesn’t exist already. The statuses are stored in the Twitter database in a table named "tweet" if a record of the status doesn't exist already.


TRIGGERS

1. The program user provides the screen name of a twitter user.


PRECONDITIONS

1. Twitter connection is available.
2. The screen name belongs to a valid Twitter user.
3. Database connection is available.


GOALS

1. Store (userID, userCreateDate, screenName, followers, following, verified) in the "user" table of the database.
2. Store (tweetID, userID, statusCreateDate, tweetText, url, favCount, rtCount) in the "tweet" table of the database.


STEPS OF EXECUTION

1. The program connects to Twitter.
2. The program user provides a screen name.
3. Twitter returns information about the screen name.
4. Twitter returns the last 200 statuses posted by the screen name.
5. The program connects to the db.
6. The program attempts to add the user information to the db.
7. The program attempts to add the statuses to the db.
8. The program closes connection to the db.
