package ua.nure.voitenkom.SummaryTask4.db.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.holder.ConnectionHolder;

import java.util.List;

/**
 * Created by Maria on 30.07.2015.
 */
public class AbstractRepository<T> implements IAbstractRepository<T>{

    private static final Logger logger = LoggerFactory.getLogger(AbstractRepository.class);
    private final ConnectionHolder connectionHolder;

    public AbstractRepository(ConnectionHolder connectionHolder) {
        this.connectionHolder = connectionHolder;
    }

//TODO
    @Override
    public T findById(int id) {
        return null;
    }

    @Override
    public int findByName(String name) {
        return 0;
    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public void add(T entity) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void update(T entity) {

    }
}
