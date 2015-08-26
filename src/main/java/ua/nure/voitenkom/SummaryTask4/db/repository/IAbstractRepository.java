package ua.nure.voitenkom.SummaryTask4.db.repository;

import ua.nure.voitenkom.SummaryTask4.db.entity.Entity;

import java.util.List;

/**
 * @author Mariia Voitenko
 */
public interface IAbstractRepository<T extends Entity> {

    T selectById(int id);

    List<T> selectAll();

    void insert(T entity);

    void update(T entity);

    void deleteById(int id);

}
