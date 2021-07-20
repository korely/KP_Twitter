package ua.kate.KP_Twitter.persistence;

import ua.kate.KP_Twitter.model.Tweet;
import ua.kate.KP_Twitter.model.User;

import java.sql.*;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

public class TweetDaoJdbcImpl implements TweetDao {

    Connection connection;
    {
        try {
            connection = H2Connection.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Long add(Tweet model) {
        Long key = null;
        String sql = "INSERT INTO TWEET (userId" +
                ", content) VALUES (?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, model.getUser().getId());
            statement.setString(2, model.getContent());
            int result = statement.executeUpdate();
            System.out.println("Tweet " + model.getContent() + " was added");
            if (result == 1) {
                ResultSet keys = statement.getGeneratedKeys();
                if (keys.next()){
                    key = keys.getLong(1);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return key;
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
                    tweet = new Tweet(id, content, user.get());
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
                        tweet = new Tweet(id, content, user.get());
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
}
