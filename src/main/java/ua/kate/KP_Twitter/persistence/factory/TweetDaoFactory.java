package ua.kate.KP_Twitter.persistence.factory;

import ua.kate.KP_Twitter.model.Tweet;
import ua.kate.KP_Twitter.persistence.dao.GenericDao;
import ua.kate.KP_Twitter.persistence.dao.TweetDaoInMemoryImpl;
import ua.kate.KP_Twitter.persistence.dao.TweetDaoJdbcImpl;

public class TweetDaoFactory implements DaoFactory<Tweet>{
    @Override
    public GenericDao<Tweet> createInMemoryImpl() {
        return new TweetDaoInMemoryImpl();
    }

    @Override
    public GenericDao<Tweet> createJdbcImpl() {
        return new TweetDaoJdbcImpl();
    }
}
