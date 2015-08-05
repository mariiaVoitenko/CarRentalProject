package ua.nure.voitenkom.SummaryTask4.db.repository;

import ua.nure.voitenkom.SummaryTask4.db.extractor.IExtractor;

import java.util.List;

/**
 * Created by Maria on 30.07.2015.
 */
public interface IAbstractRepository<T> {
    T selectById(int id, String sql, IExtractor<T> extractor);

    int selectByName(String name, String sql, IExtractor<T> extractor);

    List<T> selectAll(String sql, IExtractor<T> extractor);
}
