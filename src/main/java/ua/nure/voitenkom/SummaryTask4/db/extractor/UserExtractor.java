package ua.nure.voitenkom.SummaryTask4.db.extractor;

import ua.nure.voitenkom.SummaryTask4.db.FieldsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.Brand;
import ua.nure.voitenkom.SummaryTask4.db.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserExtractor implements IExtractor<User> {
    @Override
    public User extract(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(FieldsContainer.FIELD_ID);
        String name = resultSet.getString(FieldsContainer.FIELD_FULL_NAME);
        boolean isBlocked = resultSet.getBoolean(FieldsContainer.FIELD_IS_BLOCKED);
        boolean isRegistered = resultSet.getBoolean(FieldsContainer.FIELD_IS_REGISTERED);
        String token = resultSet.getString(FieldsContainer.FIELD_REGISTRATION_TOKEN);
        String passportNumber = resultSet.getString(FieldsContainer.FIELD_PASSPORT_NUMBER);
        String photoPath = resultSet.getString(FieldsContainer.FIELD_PHOTO);
        String password = resultSet.getString(FieldsContainer.FIELD_PASSWORD);
        String login = resultSet.getString(FieldsContainer.FIELD_LOGIN);
        int roleId = resultSet.getInt(FieldsContainer.FIELD_ROLES_ID);
        return new User(id, name, isBlocked, isRegistered,token,passportNumber,roleId,photoPath,password,login);
    }
}
