package ua.nure.voitenkom.SummaryTask4.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author MariiaVoitenko
 */
public interface IExtractor<T> {

    T extract(ResultSet resultSet) throws SQLException;

}
