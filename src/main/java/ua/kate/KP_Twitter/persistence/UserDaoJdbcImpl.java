package ua.kate.KP_Twitter.persistence;

import ua.kate.KP_Twitter.model.User;

import java.sql.*;
import java.util.*;

public class UserDaoJdbcImpl implements UserDao {

    private static final String getLastId = "SELECT MAX(userId) FROM USER";
    Connection connection = new h2Connection().getConnection();

    @Override
    public Long add(User model) {

        String sql = "INSERT INTO USER (login" +
                ", nickname" +
                ", about) VALUES (?, ?, ?)";

        try {
            PreparedStatement statement1 = connection.prepareStatement(sql);
            statement1.setString(1, model.getLogin());
            statement1.setString(2, model.getNickname());
            statement1.setString(3, model.getAbout());
            int result = statement1.executeUpdate();
            System.out.println("User " + model.getNickname() + " was added");
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
    public Set<User> getAll() {
        Set<User> allUsers = new TreeSet<>();

        String sql = "SELECT * FROM USER";

        try (
                PreparedStatement prStatement = connection.prepareStatement(sql);
                ResultSet rs = prStatement.executeQuery()
        ) {
            while (rs.next()) {
                long id = rs.getLong("userId");
                String login = rs.getString("login");
                String nickname = rs.getString("nickname");
                String about = rs.getString("about");

                User user = saveUserFromDb(id, login, nickname, about);

                allUsers.add(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return allUsers;
    }

    @Override
    public Optional<User> findById(Long id) {
        String sql = "SELECT * FROM USER WHERE userId =" + id;
        Optional<User> userOpt = Optional.empty();

        try (
                PreparedStatement prStatement = connection.prepareStatement(sql);
                ResultSet rs = prStatement.executeQuery()
        ) {
            while (rs.next()) {
                long userId = rs.getLong("userId");
                if (userId == id) {
                    String login = rs.getString("login");
                    String nickname = rs.getString("nickname");
                    String about = rs.getString("about");

                    User user = saveUserFromDb(id, login, nickname, about);
                    userOpt = Optional.of(user);

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userOpt;
    }

    @Override
    public void updateById(Long id, User model) {
        String sql = "UPDATE USER SET login = ?, nickname = ?, about = ? WHERE userId =" + id;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, model.getLogin());
            statement.setString(2, model.getNickname());
            statement.setString(3, model.getAbout());
            statement.executeUpdate();

        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM USER WHERE userId = " + id;

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private User saveUserFromDb(long id, String login, String nickname, String about) {
        User user = new User();
        user.setId(id);
        user.setLogin(login);
        user.setNickname(nickname);
        user.setAbout(about);
        return user;
    }
}
