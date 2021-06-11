package ua.kate.KP_Twitter.persistence;

import ua.kate.KP_Twitter.model.Tweet;
import ua.kate.KP_Twitter.storage.Storage;

import java.util.Optional;
import java.util.Set;

public class TweetDaoInMemoryImpl implements TweetDao {
    @Override
    public Long save(Tweet model) {
        return Storage.getInstance().putTweetInStorage(model);
    }

    @Override
    public Set<Tweet> getAll() {
        return Storage.getInstance().getTweets();
    }

    @Override
    public Optional<Tweet> findById(Long id) {
        return Optional.of(Storage.getInstance().getTweetByKey(id));
    }

    @Override
    public void updateById(Long id, Tweet model) {
        model.setId(id);
        Storage.getInstance().putTweetInStorage(model);
    }

    @Override
    public void deleteById(Long id) {
        Storage.getInstance().removeTweetByKey(id);
    }
}