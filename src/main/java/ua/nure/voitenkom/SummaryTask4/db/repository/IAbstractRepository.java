package ua.nure.voitenkom.SummaryTask4.db.repository;

import ua.nure.voitenkom.SummaryTask4.db.extractor.IExtractor;

import java.util.List;

/**
 * Created by Maria on 30.07.2015.
 */
public interface IAbstractRepository<T> {
    T findById(int id, String sql, IExtractor<T> extractor);

    int findByName(String name,String sql, IExtractor<T> extractor);

    List<T> findAll(String sql, IExtractor<T> extractor);

    void deleteById(int id, String sql);
}
