Twitter User Search


USE CASE

1. The program user provides the screen name of a Twitter user. Twitter returns information about the screen name and the last 200 statuses posted by the screen name. Information about the screen name is stored in the Twitter database user table if a record for the screen name doesn’t exist already. The statuses are stored in the Twitter database tweet table.


TRIGGERS

1. The program user provides the screen name of a twitter user.


ACTORS

1. A program user who provides a screen name.
2. A twitter user who owns the screen name.
3. Twitter.
4. MySQL Twitter Database.


PRECONDITIONS

1. Twitter connection is available.
2. The screen name belongs to a valid Twitter user.
3. Database connection is available.


GOALS

1. Store userID, userCreateDate, screenName, followers, following, and verified in the user table of the Twitter DB.
2. Store tweetID, userID, statusCreateDate, tweetText, url, favCount, and rtCount in the tweet table of the TwitterDB.


NOT AVAILABLE

1. Provide error message describing failed conclusion.


STEPS OF EXECUTION

1. The program connects to Twitter.
2. The program user provides a screen name.
3. Twitter returns information about the screen name.
4. Twitter returns the last 200 statuses posted by the screen name.
5. The program connects to the DB.
6. The program adds the user information to the user table in the DB if it doesn’t exist already.
7. The program adds the statuses to the tweet table in the DB.
8. The program closes connection to the DB.