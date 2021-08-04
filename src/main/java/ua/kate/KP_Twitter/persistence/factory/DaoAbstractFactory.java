package ua.kate.KP_Twitter.persistence.factory;

import ua.kate.KP_Twitter.model.Tweet;
import ua.kate.KP_Twitter.model.User;
import ua.kate.KP_Twitter.persistence.dao.DaoType;
import ua.kate.KP_Twitter.persistence.dao.GenericDao;

public class DaoAbstractFactory<T> {

    private final UserDaoFactory userDaoFactory;
    private final TweetDaoFactory tweetDaoFactory;
    private final DaoType daoType;

    public DaoAbstractFactory(DaoType daoType) {
        this.daoType = daoType;
        this.userDaoFactory = new UserDaoFactory();
        this.tweetDaoFactory = new TweetDaoFactory();
    }

    public GenericDao<User> createUserDao(){
        return userDaoFactory.createDao(daoType);
    }
    public GenericDao<Tweet> createTweetDao(){
        return tweetDaoFactory.createDao(daoType);
    }
}
