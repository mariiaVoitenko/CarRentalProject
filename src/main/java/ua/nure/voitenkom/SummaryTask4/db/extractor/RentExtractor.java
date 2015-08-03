package ua.nure.voitenkom.SummaryTask4.db.extractor;

import ua.nure.voitenkom.SummaryTask4.db.FieldsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.Brand;
import ua.nure.voitenkom.SummaryTask4.db.entity.Rent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.FileHandler;

/**
 * Created by Maria on 03.08.2015.
 */
public class RentExtractor implements IExtractor<Rent> {
    @Override
    public Rent extract(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(FieldsContainer.FIELD_ID);
        boolean isDriven = resultSet.getBoolean(FieldsContainer.FIELD_IS_DRIVEN);
        int days = resultSet.getInt(FieldsContainer.FIELD_DAYS);
        int carId = resultSet.getInt(FieldsContainer.FIELD_CARS_ID);
        int userId = resultSet.getInt(FieldsContainer.FIELD_USERS_ID);
        int declineId = resultSet.getInt(FieldsContainer.FIELD_DECLINES_ID);
        int checkId = resultSet.getInt(FieldsContainer.FIELD_CHECKS_ID);
        return new Rent(id, isDriven, days, carId, userId, declineId, checkId);
    }
}
