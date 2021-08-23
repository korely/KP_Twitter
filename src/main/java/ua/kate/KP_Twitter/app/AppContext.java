package ua.kate.KP_Twitter.app;

import ua.kate.KP_Twitter.config.Configuration;
import ua.kate.KP_Twitter.persistence.dao.TweetDao;
import ua.kate.KP_Twitter.persistence.dao.UserDao;

public class AppContext {

    private final Configuration configuration;
    private final UserDao userDao;
    private final TweetDao tweetDao;

    public AppContext(Configuration configuration, UserDao userDao, TweetDao tweetDao) {
        this.configuration = configuration;
        this.userDao = userDao;
        this.tweetDao = tweetDao;
    }



}
