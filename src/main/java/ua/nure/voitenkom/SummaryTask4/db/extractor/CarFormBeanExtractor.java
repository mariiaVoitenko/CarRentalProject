package ua.nure.voitenkom.SummaryTask4.db.extractor;

import ua.nure.voitenkom.SummaryTask4.db.FieldsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.Car;
import ua.nure.voitenkom.SummaryTask4.formbean.CarFormBean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarFormBeanExtractor implements IExtractor<CarFormBean> {

    @Override
    public CarFormBean extract(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(FieldsContainer.FIELD_ID);
        String model = resultSet.getString(FieldsContainer.FIELD_MODEL);
        int price = resultSet.getInt(FieldsContainer.FIELD_PRICE);
        int doorsCount = resultSet.getInt(FieldsContainer.FIELD_DOORS_COUNT);
        boolean hasConditioner = resultSet.getBoolean(FieldsContainer.FIELD_HAS_CONDITIONER);
        int bigLuggageCount = resultSet.getInt(FieldsContainer.FIELD_BIG_LUGGAGE_COUNT);
        int smallLuggageCount = resultSet.getInt(FieldsContainer.FIELD_SMALL_LUGGAGE_COUNT);
        int sitsCount = resultSet.getInt(FieldsContainer.FIELD_SITS_COUNT);
        String photoPath = resultSet.getString(FieldsContainer.FIELD_PHOTO);
        String className = resultSet.getString(FieldsContainer.FIELD_CLASS_NAME);
        String brandName = resultSet.getString(FieldsContainer.FIELD_BRAND_NAME);
        String colorName = resultSet.getString(FieldsContainer.FIELD_COLOR_NAME);
        String statusName = resultSet.getString(FieldsContainer.FIELD_STATUS_NAME);
        return new CarFormBean(id, model, price, doorsCount, hasConditioner, bigLuggageCount,
                smallLuggageCount, sitsCount, brandName, colorName, className, statusName, photoPath);
    }
}
