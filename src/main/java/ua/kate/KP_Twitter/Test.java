package ua.kate.KP_Twitter;

import ua.kate.KP_Twitter.model.Tweet;
import ua.kate.KP_Twitter.model.User;
import ua.kate.KP_Twitter.model.UserFeed;

import java.util.Arrays;
import java.util.HashSet;

public class Test {

    public static void main(String[] args) {

        User user = new User("John55", "John");
        user.setUserId(1L);

        User user2 = new User("Eric434", "Eric");
        user.setUserId(2L);

        Tweet tweet = new Tweet(user, "Hello everyone", null);
        Tweet tweet2 = new Tweet(user2, "Hello @Eric434", new HashSet<>(Arrays.asList(user)));

        System.out.println(user);
        System.out.println(user2);
        System.out.println(tweet);
        System.out.println(tweet2);

        UserFeed userFeed = new UserFeed(new HashSet<>(Arrays.asList(tweet, tweet2)), user.getUserId(), false);
        UserFeed user2Feed = new UserFeed(new HashSet<>(Arrays.asList(tweet2)), user2.getUserId(), false);

        System.out.println(userFeed);
        System.out.println(user2Feed);

    }

}
