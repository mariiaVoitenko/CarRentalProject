package ua.nure.voitenkom.SummaryTask4.db.extractor;

import ua.nure.voitenkom.SummaryTask4.db.FieldsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.Brand;
import ua.nure.voitenkom.SummaryTask4.db.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Maria on 03.08.2015.
 */
public class RoleExtractor implements IExtractor<Role> {

    @Override
    public Role extract(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(FieldsContainer.FIELD_ID);
        String name = resultSet.getString(FieldsContainer.FIELD_NAME);
        return new Role(id, name);
    }
}
