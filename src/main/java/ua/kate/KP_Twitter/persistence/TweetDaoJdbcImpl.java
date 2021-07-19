package ua.kate.KP_Twitter.persistence;

import ua.kate.KP_Twitter.model.Tweet;
import ua.kate.KP_Twitter.model.User;

import java.sql.*;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

public class TweetDaoJdbcImpl implements TweetDao {

    private static final String getLastId = "SELECT MAX(tweetId) FROM TWEET";
    Connection connection = new h2Connection().getConnection();

    @Override
    public Long add(Tweet model) {
        String sql = "INSERT INTO TWEET (userId" +
                ", content) VALUES (?, ?)";

        try {
            PreparedStatement statement1 = connection.prepareStatement(sql);
            statement1.setLong(1, model.getUser().getId());
            statement1.setString(2, model.getContent());
            int result = statement1.executeUpdate();
            System.out.println("Tweet " + model.getContent() + " was added");
            if (result == 1) {
                Statement statement2 = connection.createStatement();
                ResultSet rs = statement2.executeQuery(getLastId);
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public Set<Tweet> getAll() {
        Set<Tweet> allTweets = new TreeSet<>();
        Tweet tweet = null;

        String sql = "SELECT * FROM TWEET";

        try (
                PreparedStatement prStatement = connection.prepareStatement(sql);
                ResultSet rs = prStatement.executeQuery()
        ) {
            while (rs.next()) {
                long id = rs.getLong("tweetId");
                long userId = rs.getLong("userId");
                String content = rs.getString("content");
                
                UserDaoJdbcImpl udji = new UserDaoJdbcImpl();
                Optional<User> user = udji.findById(userId);
                
                if(user.isPresent()){
                    tweet = saveTweetFromDb(id, content, user.get());
                }
                
                allTweets.add(tweet);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return allTweets;
    }
    
    @Override
    public Optional<Tweet> findById(Long id) {
        String sql = "SELECT * FROM TWEET WHERE tweetId =" + id;
        Optional<Tweet> tweetOpt = Optional.empty();
        Tweet tweet = null;

        try (
                PreparedStatement prStatement = connection.prepareStatement(sql);
                ResultSet rs = prStatement.executeQuery()
        ) {
            while (rs.next()) {
                long tweetId = rs.getLong("tweetId");
                if (tweetId == id) {
                    long userId = rs.getLong("userId");
                    String content = rs.getString("content");

                    UserDaoJdbcImpl udji = new UserDaoJdbcImpl();
                    Optional<User> user = udji.findById(userId);

                    if(user.isPresent()){
                        tweet = saveTweetFromDb(id, content, user.get());
                    }
                    if (tweet != null) {
                        tweetOpt = Optional.of(tweet);
                    }

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tweetOpt;
    }

    @Override
    public void updateById(Long id, Tweet model) {
        String sql = "UPDATE TWEET SET userId = ?, content = ? WHERE tweetId =" + id;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, model.getUser().getId());
            statement.setString(2, model.getContent());
            statement.executeUpdate();

        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM TWEET WHERE tweetId = " + id;

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Tweet saveTweetFromDb(long id, String content, User user) {
        Tweet tweet = new Tweet();
        tweet.setId(id);
        tweet.setUser(user);
        tweet.setContent(content);
        return tweet;
    }
}
