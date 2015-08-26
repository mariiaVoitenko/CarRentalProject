package ua.nure.voitenkom.SummaryTask4.db.extractor;

import ua.nure.voitenkom.SummaryTask4.db.FieldsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.Rent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * @author MariiaVoitenko
 */
public class RentExtractor implements IExtractor<Rent> {

    @Override
    public Rent extract(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(FieldsContainer.FIELD_ID);
        boolean isDriven = resultSet.getBoolean(FieldsContainer.FIELD_IS_DRIVEN);
        int carId = resultSet.getInt(FieldsContainer.FIELD_CARS_ID);
        int userId = resultSet.getInt(FieldsContainer.FIELD_USERS_ID);
        int declineId = resultSet.getInt(FieldsContainer.FIELD_DECLINES_ID);
        int checkId = resultSet.getInt(FieldsContainer.FIELD_CHECKS_ID);
        Timestamp startDate = resultSet.getTimestamp(FieldsContainer.FIELD_START_DATE);
        Timestamp endDate = resultSet.getTimestamp(FieldsContainer.FIELD_END_DATE);
        boolean isReturned = resultSet.getBoolean(FieldsContainer.FIELD_IS_RETURNED);
        boolean isApproved = resultSet.getBoolean(FieldsContainer.FIELD_IS_APPROVED);
        boolean isFinished = resultSet.getBoolean(FieldsContainer.FIELD_IS_FINISHED);
        return new Rent(id, isDriven, carId, userId, declineId, checkId, startDate, endDate,
                isReturned, isApproved, isFinished);
    }

}
