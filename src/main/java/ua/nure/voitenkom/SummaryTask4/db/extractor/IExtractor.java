package ua.nure.voitenkom.SummaryTask4.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IExtractor<T> {

    T extract(ResultSet resultSet) throws SQLException;

}
