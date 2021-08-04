package ua.kate.KP_Twitter;

import ua.kate.KP_Twitter.model.Tweet;
import ua.kate.KP_Twitter.model.User;
import ua.kate.KP_Twitter.model.UserFeed;
import ua.kate.KP_Twitter.persistence.dao.TweetDao;
import ua.kate.KP_Twitter.persistence.dao.TweetDaoJdbcImpl;
import ua.kate.KP_Twitter.persistence.dao.UserDao;
import ua.kate.KP_Twitter.persistence.dao.UserDaoJdbcImpl;

import java.util.*;

public class Test {

    public static void main(String[] args) {

//CREATE

        User user = new User("John55", "John");
        user.setId(25L);
        user.setAbout("My name is John");

        User user2 = new User("Eric434", "Eric", "about");
        user2.setId(24L);

        User user3 = new User("New3434", "NewOne", "About description");
        user3.setId(8L);

        User userEricHaha = new User("Eric434", "RealEric", "Eric about hahaha");
        userEricHaha.setId(105L);

        Tweet tweet = new Tweet(user, "Hello everyone");
        tweet.setId(11L);

        Tweet tweet2 = new Tweet(user2, "Hello @Eric434", new HashSet<>(Collections.singletonList(user)));
        tweet2.setId(15L);

        Tweet tweet2FromMain = new Tweet(user3, "Hello again");
        tweet2FromMain.setId(25L);

        Tweet newTweet = new Tweet(user, "This is an absolutely new about");
        tweet2FromMain.setId(435L);

        UserFeed userFeed = new UserFeed(new HashSet<>(Arrays.asList(tweet, tweet2)), user.getId(), false);
        UserFeed user2Feed = new UserFeed(new HashSet<>(Collections.singletonList(tweet2)), user2.getId(), false);


        //CHANGE DAO
        ///////////////////////////////////////////////////////////////////
        UserDao userDao = new UserDaoJdbcImpl();
        //////////////////////////////////////////////////////////////////

//        Tweet karlTweet = new Tweet(userDao.findById(74L).get(), "This is not new about");

        //ADD
//        userDao.add(user3);
//        System.out.println(user3);
//        userDao.add(user2);


//        //FINDUSERBYID
//        Optional<User> user2FromDB = userDao.findById(4L);
//        System.out.println(user2FromDB);

        //UPDATEUBYID

//        System.out.println(userDao.findById(24L));
//        userDao.updateById(24L, user2);
//        System.out.println(userDao.findById(24L));

        //DELETEBYID
//        userDao.deleteById(15L);

        //GETALL
        System.out.println(userDao.getAll());

        System.out.println("-----------------------------");
        ///////////////////////////////////////////////////////////////////
        TweetDao tweetDao = new TweetDaoJdbcImpl();
        //////////////////////////////////////////////////////////////////

        //ADD
//        tweetDao.add(tweet);
//        tweetDao.add(tweet2);
//        tweetDao.add(tweet2FromMain);
//        tweetDao.add(karlTweet);

        //FINDUSERBYID

//        Optional<Tweet> tweet2FromDB = tweetDao.findById(15L);
//        System.out.println(tweet2FromDB);

        //UPDATEUBYID
//        System.out.println(tweetDao.findById(11L));
//        tweetDao.updateById(11L, tweet2FromMain);
//        System.out.println(tweetDao.findById(11L));


        //DELETEBYID
//        tweetDao.deleteById(19L);

        //GETALL
        System.out.println(tweetDao.getAll());
    }

}
