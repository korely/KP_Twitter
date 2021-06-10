package ua.kate.KP_Twitter;

import ua.kate.KP_Twitter.model.Tweet;
import ua.kate.KP_Twitter.model.User;
import ua.kate.KP_Twitter.model.UserFeed;
import ua.kate.KP_Twitter.persistence.TweetDao;
import ua.kate.KP_Twitter.persistence.TweetDaoInMemoryImpl;
import ua.kate.KP_Twitter.persistence.UserDao;
import ua.kate.KP_Twitter.persistence.UserDaoInMemoryImpl;

import java.util.*;

public class Test {

    public static void main(String[] args) {

        User user = new User("John55", "John");
        user.setId(1L);

        User user2 = new User("Eric434", "Eric");
        user2.setId(2L);

        User user3 = new User("New3434", "NewOne");
        user3.setId(3L);

        Tweet tweet = new Tweet(user, "Hello everyone", null);
        tweet.setId(11L);

        Tweet tweet2 = new Tweet(user2, "Hello @Eric434", new HashSet<>(Collections.singletonList(user)));
        tweet2.setId(22L);
//
//        System.out.println(user);
//        System.out.println(user2);
//        System.out.println(tweet);
//        System.out.println(tweet2);
//
        UserFeed userFeed = new UserFeed(new HashSet<>(Arrays.asList(tweet, tweet2)), user.getId(), false);
        UserFeed user2Feed = new UserFeed(new HashSet<>(Collections.singletonList(tweet2)), user2.getId(), false);

//        System.out.println(userFeed);
//        System.out.println(user2Feed);


        ///////////////////////////////////////////////////////////////////
        UserDao userDao = new UserDaoInMemoryImpl();
        //////////////////////////////////////////////////////////////////

        userDao.save(user);
        userDao.save(user2);

        Set<User> users = userDao.getAll();
        System.out.println(users);

        Optional<User> user2FromDB = userDao.findById(user2.getId());

        User user2FromMain = new User ("Eric434", "RealEric");

        userDao.updateById(user2.getId(), user2FromMain);

        userDao.deleteById(user.getId());

        users = userDao.getAll();
        System.out.println(users);

        System.out.println("end for user");

        ///////////////////////////////////////////////////////////////////
        TweetDao tweetDao = new TweetDaoInMemoryImpl();
        //////////////////////////////////////////////////////////////////

        tweetDao.save(tweet);
        tweetDao.save(tweet2);

        Set<Tweet> tweets = tweetDao.getAll();
        System.out.println(tweets);

        Optional<Tweet> tweet2FromDB = tweetDao.findById(tweet2.getId());

        Tweet tweet2FromMain = new Tweet (user3, "Hello again", null);

        tweetDao.updateById(tweet2.getId(), tweet2FromMain);

        tweetDao.deleteById(tweet.getId());

        tweets = tweetDao.getAll();
        System.out.println(tweets);

        System.out.println("end for tweet");
    }

}
