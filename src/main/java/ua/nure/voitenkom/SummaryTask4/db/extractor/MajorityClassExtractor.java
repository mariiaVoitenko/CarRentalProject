package ua.nure.voitenkom.SummaryTask4.db.extractor;

import ua.nure.voitenkom.SummaryTask4.db.FieldsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.Brand;
import ua.nure.voitenkom.SummaryTask4.db.entity.MajorityClass;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Maria on 03.08.2015.
 */
public class MajorityClassExtractor implements IExtractor<MajorityClass> {

    @Override
    public MajorityClass extract(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(FieldsContainer.FIELD_ID);
        String name = resultSet.getString(FieldsContainer.FIELD_NAME);
        return new MajorityClass(id, name);
    }
}
