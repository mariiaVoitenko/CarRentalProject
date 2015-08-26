package ua.nure.voitenkom.SummaryTask4.db.repository.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.holder.IConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.service.account.PasswordMaker;
import ua.nure.voitenkom.SummaryTask4.db.StatementsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.User;
import ua.nure.voitenkom.SummaryTask4.db.extractor.UserExtractor;
import ua.nure.voitenkom.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.AbstractRepository;
import ua.nure.voitenkom.SummaryTask4.exception.DatabaseException;

import static ua.nure.voitenkom.SummaryTask4.validation.ValidationManager.isNull;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserRepository extends AbstractRepository<User> implements IUserRepository {

    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    public UserRepository(IConnectionHolder connectionHolder) {
        super(connectionHolder);
    }

    @Override
    public User selectById(int id) {
        return super.selectById(id, StatementsContainer.SQL_SELECT_USER_BY_ID, new UserExtractor());
    }

    @Override
    public User findByLogin(String login) {
        return selectByStringParameter(StatementsContainer.SQL_SELECT_USER_BY_LOGIN, login);
    }

    @Override
    public void makeBlocked(int id) {
        changeState(StatementsContainer.SQL_UPDATE_USER_BLOCKED, id);
    }

    @Override
    public void changeRole(int roleId, int userId) {
        String sql = StatementsContainer.SQL_UPDATE_USER_ROLE;
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, roleId);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    @Override
    public List<User> selectByRoleId(int roleId) {
        String sql = StatementsContainer.SQL_SELECT_USER_BY_ROLE_ID;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, roleId);
            return executeQuery(preparedStatement, new UserExtractor());
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    @Override
    public void updatePhoto(User user) {
        String sql = StatementsContainer.SQL_UPDATE_USER_PHOTO;
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, user.getPhotoPath());
            ps.setInt(2, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    @Override
    public void unblock(int id) {
        changeState(StatementsContainer.SQL_UPDATE_USER_BLOCKED_NOT, id);
    }

    @Override
    public void setRegisteredState(int id) {
        changeState(StatementsContainer.SQL_UPDATE_USER_IS_REGISTERED, id);
    }

    @Override
    public boolean checkPassword(String login, String password) {
        User user = findByLogin(login);
        String cipherPassword = PasswordMaker.makePassword(password);
        return !isNull(user) && (user.getPassword().equals(cipherPassword));
    }

    @Override
    public List<User> selectAll() {
        return super.selectAll(StatementsContainer.SQL_SELECT_ALL_USERS, new UserExtractor());
    }

    @Override
    public void deleteById(int id) {
        super.deleteById(id, StatementsContainer.SQL_DELETE_USER_BY_ID);
    }

    @Override
    public User selectByToken(String token) {
        return selectByStringParameter(StatementsContainer.SQL_SELECT_USER_BY_TOKEN, token);
    }

    @Override
    public void insert(User user) {
        String sql = StatementsContainer.SQL_INSERT_USER;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setString(2, user.getRegistrationToken());
            preparedStatement.setString(3, user.getPassportNumber());
            preparedStatement.setInt(4, user.getRoleId());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getLogin());
            preparedStatement.setTimestamp(7, user.getRegistrationTime());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    @Override
    public void update(User entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void insertWithPhoto(User user) {
        String sql = StatementsContainer.SQL_INSERT_USER_WITH_PHOTO;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setString(2, user.getRegistrationToken());
            preparedStatement.setString(3, user.getPassportNumber());
            preparedStatement.setInt(4, user.getRoleId());
            preparedStatement.setString(5, user.getPhotoPath());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setString(7, user.getLogin());
            preparedStatement.setTimestamp(8, user.getRegistrationTime());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    private void changeState(String sql, int id){
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    private User selectByStringParameter(String sql, String parameter){
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, parameter);
            List<User> records = executeQuery(preparedStatement, new UserExtractor());
            return records.isEmpty() ? null : records.get(0);
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

}
