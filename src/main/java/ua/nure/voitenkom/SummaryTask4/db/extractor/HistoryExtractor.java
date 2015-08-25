package ua.nure.voitenkom.SummaryTask4.db.extractor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.FieldsContainer;
import ua.nure.voitenkom.SummaryTask4.formbean.HistoryFormBean;

import java.sql.ResultSet;
import java.sql.SQLException;

import static ua.nure.voitenkom.SummaryTask4.util.DateManager.timestampToString;

public class HistoryExtractor implements IExtractor<HistoryFormBean> {

    private static final Logger logger = LoggerFactory.getLogger(HistoryExtractor.class);

    @Override
    public HistoryFormBean extract(ResultSet resultSet) throws SQLException {
        String model = resultSet.getString(FieldsContainer.FIELD_MODEL);
        String brand = resultSet.getString(FieldsContainer.FIELD_BRAND_NAME);
        String photoPath = resultSet.getString(FieldsContainer.FIELD_PHOTO);
        int rentId = resultSet.getInt(FieldsContainer.FIELD_RENT_ID);
        int doorsCount = resultSet.getInt(FieldsContainer.FIELD_DOORS_COUNT);
        boolean hasConditioner = resultSet.getBoolean(FieldsContainer.FIELD_HAS_CONDITIONER);
        int bigLuggageCount = resultSet.getInt(FieldsContainer.FIELD_BIG_LUGGAGE_COUNT);
        int smallLuggageCount = resultSet.getInt(FieldsContainer.FIELD_SMALL_LUGGAGE_COUNT);
        int sitsCount = resultSet.getInt(FieldsContainer.FIELD_SITS_COUNT);
        int checkSum = resultSet.getInt(FieldsContainer.FIELD_SUM);
        boolean isPayed = resultSet.getBoolean(FieldsContainer.FIELD_IS_PAYED);
        boolean isDriven = resultSet.getBoolean(FieldsContainer.FIELD_IS_DRIVEN);
        String startDate = timestampToString(resultSet.getTimestamp(FieldsContainer.FIELD_START_DATE));
        String endDate = timestampToString(resultSet.getTimestamp(FieldsContainer.FIELD_END_DATE));
        String color = resultSet.getString(FieldsContainer.FIELD_COLOR_NAME);
        String className = resultSet.getString(FieldsContainer.FIELD_CLASS_NAME);
        boolean isApproved = resultSet.getBoolean(FieldsContainer.FIELD_IS_APPROVED);
        boolean isReturned = resultSet.getBoolean(FieldsContainer.FIELD_IS_RETURNED);
        boolean isFinished = resultSet.getBoolean(FieldsContainer.FIELD_IS_FINISHED);
        String declineName = "";
        try {
            declineName = resultSet.getString(FieldsContainer.FIELD_DECLINE_NAME);
        } catch (SQLException e) {
            logger.warn("This rent wasn't declined. It's decline name is null. It's ok");
        }
        return new HistoryFormBean(bigLuggageCount, brand, checkSum, className, color, doorsCount, hasConditioner,
                endDate, isApproved, isDriven, isFinished, isPayed, isReturned, photoPath, model, sitsCount, rentId,
                smallLuggageCount, startDate, declineName);
    }

}
