package ua.nure.voitenkom.SummaryTask4.db.repository.role;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.StatementsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.Role;
import ua.nure.voitenkom.SummaryTask4.db.extractor.IExtractor;
import ua.nure.voitenkom.SummaryTask4.db.extractor.RoleExtractor;
import ua.nure.voitenkom.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.AbstractRepository;
import ua.nure.voitenkom.SummaryTask4.exception.DatabaseException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Maria on 03.08.2015.
 */
public class RoleRepository extends AbstractRepository<Role> implements IRoleRepository {

    private static final Logger logger = LoggerFactory.getLogger(RoleRepository.class);

    public RoleRepository(ConnectionHolder connectionHolder) {
        super(connectionHolder);
    }

    @Override
    public Role findById(int id) {
        return super.findById(id, StatementsContainer.SQL_SELECT_ROLE_BY_ID, new RoleExtractor());
    }

    @Override
    public int findByName(String name) {
        return super.findByName(name, StatementsContainer.SQL_SELECT_ROLE_BY_NAME, new RoleExtractor());
    }

    @Override
    public List<Role> findAll(String sql, IExtractor<Role> extractor) {
        return super.findAll(StatementsContainer.SQL_SELECT_ALL_ROLES, extractor);
    }

    @Override
    public void deleteById(int id, String sql) {
        super.deleteById(id, StatementsContainer.SQL_DELETE_ROLE_BY_ID);
    }

    @Override
    public void update(Role role) {
        String sql = StatementsContainer.SQL_UPDATE_ROLE_BY_ID;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, role.getName());
            preparedStatement.setInt(2, role.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    @Override
    public void create(Role role) {
        String sql = StatementsContainer.SQL_INSERT_ROLE;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, role.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }
}
