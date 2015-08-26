package ua.nure.voitenkom.SummaryTask4.db.extractor;

import ua.nure.voitenkom.SummaryTask4.db.FieldsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.Status;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author MariiaVoitenko
 */
public class StatusExtractor implements IExtractor<Status> {

    @Override
    public Status extract(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(FieldsContainer.FIELD_ID);
        String name = resultSet.getString(FieldsContainer.FIELD_NAME);
        return new Status(id, name);
    }

}
