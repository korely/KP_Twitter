package ua.kate.KP_Twitter.persistence;

import ua.kate.KP_Twitter.model.User;
import ua.kate.KP_Twitter.storage.Storage;

import java.util.Optional;
import java.util.Set;

public class UserDaoInMemoryImpl implements UserDao {


    @Override
    public Long add(User model) {
        return Storage.getInstance().putUserInStorage(model);
    }

    @Override
    public Set<User> getAll() {
        return Storage.getInstance().getUsers();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.of(Storage.getInstance().getUserByKey(id));
    }

    @Override
    public void updateById(Long id, User model) {
        model.setId(id);
        Storage.getInstance().putUserInStorage(model);
    }

    @Override
    public void deleteById(Long id) {
        Storage.getInstance().removeUserByKey(id);
    }
}
