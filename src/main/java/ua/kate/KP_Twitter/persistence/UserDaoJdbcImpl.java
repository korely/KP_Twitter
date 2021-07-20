package ua.kate.KP_Twitter.persistence;

import ua.kate.KP_Twitter.model.User;

import java.sql.*;
import java.util.*;

public class UserDaoJdbcImpl implements UserDao {

    Connection connection;

    {
        try {
            connection = H2Connection.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Long add(User model) {

        Long key = null;
        String sql = "INSERT INTO USER (login" +
                ", nickname" +
                ", about) VALUES (?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, model.getLogin());
            statement.setString(2, model.getNickname());
            statement.setString(3, model.getAbout());
            int result = statement.executeUpdate();
            System.out.println("User " + model.getNickname() + " was added");
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

                User user = new User(id, login, nickname, about);

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

                    User user = new User(id, login, nickname, about);
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
}
