package ua.nure.voitenkom.SummaryTask4.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Maria on 31.07.2015.
 */
public interface IExtractor<T> {

    T extract(ResultSet resultSet) throws SQLException;

}
