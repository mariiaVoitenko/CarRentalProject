package ua.nure.voitenkom.SummaryTask4.db.extractor;

import ua.nure.voitenkom.SummaryTask4.db.FieldsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.Brand;
import ua.nure.voitenkom.SummaryTask4.db.entity.Check;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckExtractor implements IExtractor<Check> {

    @Override
    public Check extract(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(FieldsContainer.FIELD_ID);
        int sum = resultSet.getInt(FieldsContainer.FIELD_SUM);
        boolean isPayed = resultSet.getBoolean(FieldsContainer.FIELD_IS_PAYED);
        return new Check(id, sum, isPayed);
    }
}
