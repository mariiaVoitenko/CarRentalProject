package ua.nure.voitenkom.SummaryTask4.db.repository;

import ua.nure.voitenkom.SummaryTask4.db.entity.Entity;
import ua.nure.voitenkom.SummaryTask4.db.entity.SimpleEntity;
import ua.nure.voitenkom.SummaryTask4.db.extractor.IExtractor;

import java.util.List;

/**
 * Created by Maria on 30.07.2015.
 */
public interface IAbstractRepository<T> {

    T selectById(int id);

    List<T> selectAll();

    void insert(SimpleEntity entity);

    void update(SimpleEntity entity);

    void deleteById(int id);

}
