package ua.nure.voitenkom.SummaryTask4.db.extractor;

import sun.text.resources.iw.FormatData_iw_IL;
import ua.nure.voitenkom.SummaryTask4.db.FieldsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.Brand;
import ua.nure.voitenkom.SummaryTask4.db.entity.Rent;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.FileHandler;

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
        return new Rent(id, isDriven, carId, userId, declineId, checkId, startDate, endDate);
    }
}
