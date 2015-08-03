package ua.nure.voitenkom.SummaryTask4.db.repository;

import java.util.List;

/**
 * Created by Maria on 30.07.2015.
 */
public interface IAbstractRepository<T> {
    T findById(int id);

    int findByName(String name);

    List<T> findAll();

    void add(T entity);

    void deleteById(int id);

    void update(T entity);
}
