package ua.nure.voitenkom.SummaryTask4.db.extractor;

import ua.nure.voitenkom.SummaryTask4.db.FieldsContainer;
import ua.nure.voitenkom.SummaryTask4.formbean.ApplicationFormBean;

import static ua.nure.voitenkom.SummaryTask4.util.DateManager.timestampToString;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicationExtractor implements IExtractor<ApplicationFormBean> {

    @Override
    public ApplicationFormBean extract(ResultSet resultSet) throws SQLException {
        String model = resultSet.getString(FieldsContainer.FIELD_MODEL);
        String brand = resultSet.getString(FieldsContainer.FIELD_BRAND_NAME);
        String photoPath = resultSet.getString(FieldsContainer.FIELD_PHOTO);
        int carId = resultSet.getInt(FieldsContainer.FIELD_CAR_ID);
        int userId = resultSet.getInt(FieldsContainer.FIELD_USER_ID);
        int rentId = resultSet.getInt(FieldsContainer.FIELD_RENT_ID);
        int doorsCount = resultSet.getInt(FieldsContainer.FIELD_DOORS_COUNT);
        boolean hasConditioner = resultSet.getBoolean(FieldsContainer.FIELD_HAS_CONDITIONER);
        int bigLuggageCount = resultSet.getInt(FieldsContainer.FIELD_BIG_LUGGAGE_COUNT);
        int smallLuggageCount = resultSet.getInt(FieldsContainer.FIELD_SMALL_LUGGAGE_COUNT);
        int sitsCount = resultSet.getInt(FieldsContainer.FIELD_SITS_COUNT);
        int checkSum = resultSet.getInt(FieldsContainer.FIELD_SUM);
        String userName = resultSet.getString(FieldsContainer.FIELD_FULL_NAME);
        String passportNumber = resultSet.getString(FieldsContainer.FIELD_PASSPORT_NUMBER);
        String startDate = timestampToString(resultSet.getTimestamp(FieldsContainer.FIELD_START_DATE));
        String endDate = timestampToString(resultSet.getTimestamp(FieldsContainer.FIELD_END_DATE));
        boolean isDriven = resultSet.getBoolean(FieldsContainer.FIELD_IS_DRIVEN);
        String color = resultSet.getString(FieldsContainer.FIELD_COLOR_NAME);
        String className = resultSet.getString(FieldsContainer.FIELD_CLASS_NAME);
        return new ApplicationFormBean(userId, userName, startDate, smallLuggageCount, sitsCount, rentId, photoPath, model,
                passportNumber, isDriven, hasConditioner, endDate, doorsCount, color, className, checkSum, carId,
                bigLuggageCount, brand);
    }

}
