package ua.kate.KP_Twitter.persistence;

import ua.kate.KP_Twitter.model.PersistenceEntity;

import java.util.Optional;
import java.util.Set;

public interface GenericDao <T extends PersistenceEntity> {

    //CREATE
    Long add(T model);
    //READ
    Set <T> getAll();
    Optional<T> findById(Long id);
    //UPDATE
    void updateById(Long id, T model);
    //DELETE
    void deleteById(Long id);
}
