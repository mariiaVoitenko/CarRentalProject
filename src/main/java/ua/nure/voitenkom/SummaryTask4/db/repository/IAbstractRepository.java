package ua.nure.voitenkom.SummaryTask4.db.repository;

import ua.nure.voitenkom.SummaryTask4.db.entity.SimpleEntity;
import java.util.List;

public interface IAbstractRepository<T> {

    T selectById(int id);

    List<T> selectAll();

    void insert(SimpleEntity entity);

    void update(SimpleEntity entity);

    void deleteById(int id);

}
