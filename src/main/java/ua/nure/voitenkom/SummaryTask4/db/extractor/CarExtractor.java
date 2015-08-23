package ua.nure.voitenkom.SummaryTask4.db.extractor;

import ua.nure.voitenkom.SummaryTask4.db.FieldsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.Car;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarExtractor implements IExtractor<Car> {

    @Override
    public Car extract(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(FieldsContainer.FIELD_ID);
        String model = resultSet.getString(FieldsContainer.FIELD_MODEL);
        int price = resultSet.getInt(FieldsContainer.FIELD_PRICE);
        int doorsCount = resultSet.getInt(FieldsContainer.FIELD_DOORS_COUNT);
        boolean hasConditioner = resultSet.getBoolean(FieldsContainer.FIELD_HAS_CONDITIONER);
        int bigLuggageCount = resultSet.getInt(FieldsContainer.FIELD_BIG_LUGGAGE_COUNT);
        int smallLuggageCount = resultSet.getInt(FieldsContainer.FIELD_SMALL_LUGGAGE_COUNT);
        int sitsCount = resultSet.getInt(FieldsContainer.FIELD_SITS_COUNT);
        int classId = resultSet.getInt(FieldsContainer.FIELD_CLASSES_ID);
        int colorId = resultSet.getInt(FieldsContainer.FIELD_COLORS_ID);
        int statusId = resultSet.getInt(FieldsContainer.FIELD_STATUSES_ID);
        int brandId = resultSet.getInt(FieldsContainer.FIELD_BRANDS_ID);
        String photoPath = resultSet.getString(FieldsContainer.FIELD_PHOTO);
        return new Car(id, model, price, doorsCount, hasConditioner, bigLuggageCount,
                smallLuggageCount, sitsCount, classId, colorId, statusId, brandId, photoPath);
    }

}
