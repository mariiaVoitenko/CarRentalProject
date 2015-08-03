package ua.nure.voitenkom.SummaryTask4.db.extractor;

import ua.nure.voitenkom.SummaryTask4.db.FieldsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.Brand;
import ua.nure.voitenkom.SummaryTask4.db.entity.Decline;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Maria on 03.08.2015.
 */
public class DeclineExtractor implements IExtractor<Decline> {
    @Override
    public Decline extract(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(FieldsContainer.FIELD_ID);
        String name = resultSet.getString(FieldsContainer.FIELD_NAME);
        return new Decline(id, name);
    }
}
