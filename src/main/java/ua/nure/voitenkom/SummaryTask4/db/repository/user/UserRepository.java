package ua.nure.voitenkom.SummaryTask4.db.repository.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.service.account.PasswordMaker;
import ua.nure.voitenkom.SummaryTask4.db.StatementsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.SimpleEntity;
import ua.nure.voitenkom.SummaryTask4.db.entity.User;
import ua.nure.voitenkom.SummaryTask4.db.extractor.UserExtractor;
import ua.nure.voitenkom.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.AbstractRepository;
import ua.nure.voitenkom.SummaryTask4.exception.DatabaseException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserRepository extends AbstractRepository<User> implements IUserRepository {

    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    public UserRepository(ConnectionHolder connectionHolder) {
        super(connectionHolder);
    }

    @Override
    public User selectById(int id) {
        return super.selectById(id, StatementsContainer.SQL_SELECT_USER_BY_ID, new UserExtractor());
    }

    @Override
    public User findByLogin(String login) {
        String sql = StatementsContainer.SQL_SELECT_USER_BY_LOGIN;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, login);
            List<User> records = executeQuery(preparedStatement, new UserExtractor());
            return records.isEmpty() ? null : records.get(0);
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    @Override
    public void makeBlocked(int id) {
        String sql = StatementsContainer.SQL_UPDATE_USER_BLOCKED;
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    @Override
    public void unblock(int id) {
        String sql = StatementsContainer.SQL_UPDATE_USER_BLOCKED_NOT;
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    @Override
    public void setRegisteredState(int id) {
        String sql = StatementsContainer.SQL_UPDATE_USER_IS_REGISTERED;
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    @Override
    public boolean checkPassword(String login, String password) {
        User user = findByLogin(login);
        String cipherPassword = PasswordMaker.makePassword(password);
        return user.getPassword().equals(cipherPassword) ? true : false;
    }

    @Override
    public List<User> selectAll() {
        return super.selectAll(StatementsContainer.SQL_SELECT_ALL_USERS, new UserExtractor());
    }

    @Override
    public void insert(SimpleEntity entity) {

    }

    @Override
    public void update(SimpleEntity entity) {

    }

    @Override
    public void deleteById(int id) {
        super.deleteById(id, StatementsContainer.SQL_DELETE_USER_BY_ID);
    }

    @Override
    public User selectByToken(String token) {
        String sql = StatementsContainer.SQL_SELECT_USER_BY_TOKEN;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, token);
            List<User> records = executeQuery(preparedStatement, new UserExtractor());
            return records.isEmpty() ? null : records.get(0);
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
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
}
