package ua.kate.KP_Twitter.persistence.factory;
import ua.kate.KP_Twitter.model.PersistenceEntity;
import ua.kate.KP_Twitter.persistence.dao.DaoType;
import ua.kate.KP_Twitter.persistence.dao.GenericDao;

public interface DaoFactory<T extends PersistenceEntity> {

    default GenericDao<T> createDao(DaoType type) {
        if (DaoType.IN_MEM == type) {
            return createInMemoryImpl();
        }
        if (DaoType.JDBC == type) {
            return createJdbcImpl();
        }
        throw new RuntimeException("SAD! Could not resolve db type");
    }

    GenericDao<T> createInMemoryImpl();

    GenericDao<T> createJdbcImpl();

}