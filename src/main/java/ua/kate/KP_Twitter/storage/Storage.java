package ua.kate.KP_Twitter.storage;

import ua.kate.KP_Twitter.model.Tweet;
import ua.kate.KP_Twitter.model.User;

import java.util.*;

public final class Storage {
    private final Map<Long, Tweet> tweetStorage = new HashMap<>();
    private static final Map<Long, User> userStorage = new HashMap<>();


    private long userSequence = 0;
    private long tweetSequence = 0;

    private Storage() {
    }

    private static class StorageHolder{
        private static final Storage storage = new Storage();
    }

    public static Storage getInstance(){
        return StorageHolder.storage;
    }

    public static Set<User> getUsers() {
        Set<User> result = new HashSet<>();
        for (User user : userStorage.values()) {
//            User userCopy = createUserCopy(user);
            result.add(createUserCopy(user));
        }
        return result;
    }

    private static User createUserCopy(User original) {
        if (original != null) {
            User copy = new User();
            copy.setId(original.getId());
            copy.setAbout(original.getAbout());
            copy.setDateOfBirth(original.getDateOfBirth());
            copy.setDateRegistered(original.getDateRegistered());
            copy.setFollowers(original.getFollowers());
            copy.setFollowing(original.getFollowing());
            copy.setNickname(original.getNickname());
            copy.setLogin(original.getLogin());
            return copy;
        }
        return null;
    }

    public Set<Tweet> getTweets() {
        Set<Tweet> result = new HashSet<>();
        for (Tweet tweet : tweetStorage.values()) {
            result.add(createTweetCopy(tweet));
        }
        return result;
    }


    private static Tweet createTweetCopy(Tweet original) {
        Tweet copy = new Tweet();
        if (original != null) {
            copy.setId(original.getId());
            copy.setContent(original.getContent());
            copy.setDatePosted(original.getDatePosted());
            copy.setLikes(original.getLikes());
            copy.setReferenceTweetId(original.getReferenceTweetId());
            copy.setUser(original.getUser());
            copy.setRetweets(original.getRetweets());
            return copy;
        }
        return null;
    }

    public User getUserByKey(Long key) {
        return createUserCopy(userStorage.get(key));
    }

    public Tweet getTweetByKey(Long key) {
        return createTweetCopy(tweetStorage.get(key));
    }


    public Long putUserInStorage(User user) {
        User toBeSaved = createUserCopy(user);
        if (toBeSaved.getId() == null) {
            toBeSaved.setId(++userSequence);
            user.setId(toBeSaved.getId());
        }
        userStorage.put(toBeSaved.getId(), toBeSaved);
        return toBeSaved.getId();
    }

    public Long putTweetInStorage(Tweet tweet) {
        Tweet toBeSaved = createTweetCopy(tweet);
        if (Objects.requireNonNull(toBeSaved).getId() == null) {
            toBeSaved.setId(++tweetSequence);
            tweet.setId(toBeSaved.getId());
        }
        tweetStorage.put(toBeSaved.getId(), toBeSaved);
        return toBeSaved.getId();
    }

    public void removeUserByKey(Long key) {
        userStorage.remove(key);
    }

    public void removeTweetByKey(Long key) {
        tweetStorage.remove(key);
    }

}
