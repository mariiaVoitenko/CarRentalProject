package ua.nure.voitenkom.SummaryTask4.db.extractor;

import ua.nure.voitenkom.SummaryTask4.db.FieldsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.Brand;
import ua.nure.voitenkom.SummaryTask4.db.entity.DamageCheck;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Maria on 03.08.2015.
 */
public class DamageCheckExtractor implements IExtractor<DamageCheck> {
    @Override
    public DamageCheck extract(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(FieldsContainer.FIELD_ID);
        int damageId = resultSet.getInt(FieldsContainer.FIELD_DAMAGES_ID);
        int checkId = resultSet.getInt(FieldsContainer.FIELD_CHECKS_ID);
        return new DamageCheck(damageId,checkId,id);
    }
}
