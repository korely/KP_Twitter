package ua.kate.KP_Twitter.persistence.factory;

import ua.kate.KP_Twitter.model.User;
import ua.kate.KP_Twitter.persistence.dao.GenericDao;
import ua.kate.KP_Twitter.persistence.dao.UserDaoInMemoryImpl;
import ua.kate.KP_Twitter.persistence.dao.UserDaoJdbcImpl;

public class UserDaoFactory implements DaoFactory<User>{
    @Override
    public GenericDao<User> createInMemoryImpl() {
        return new UserDaoInMemoryImpl();
    }

    @Override
    public GenericDao<User> createJdbcImpl() {
        return new UserDaoJdbcImpl();
    }
}
