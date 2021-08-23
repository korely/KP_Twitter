package ua.kate.KP_Twitter.service;

import ua.kate.KP_Twitter.model.User;
import ua.kate.KP_Twitter.persistence.dao.UserDao;

public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User createUser(String login, String nickName){
        User user =  new User(login, nickName);
        user.setId(userDao.add(user));
        return user;
    }
}
