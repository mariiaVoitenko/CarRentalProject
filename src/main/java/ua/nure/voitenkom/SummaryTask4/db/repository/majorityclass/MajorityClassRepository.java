package ua.nure.voitenkom.SummaryTask4.db.repository.majorityclass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.StatementsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.MajorityClass;
import ua.nure.voitenkom.SummaryTask4.db.extractor.IExtractor;
import ua.nure.voitenkom.SummaryTask4.db.extractor.MajorityClassExtractor;
import ua.nure.voitenkom.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.AbstractRepository;
import ua.nure.voitenkom.SummaryTask4.exception.DatabaseException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Maria on 05.08.2015.
 */
public class MajorityClassRepository extends AbstractRepository<MajorityClass> implements IMajorityClassRepository {

    private static final Logger logger = LoggerFactory.getLogger(MajorityClassRepository.class);

    public MajorityClassRepository(ConnectionHolder connectionHolder) {
        super(connectionHolder);
    }

    @Override
    public MajorityClass findById(int id) {
        return super.selectById(id, StatementsContainer.SQL_SELECT_CLASS_BY_ID, new MajorityClassExtractor());
    }

    @Override
    public int findByName(String name) {
        return super.selectByName(name, StatementsContainer.SQL_SELECT_CLASS_BY_NAME, new MajorityClassExtractor());
    }

    @Override
    public List<MajorityClass> selectAll(String sql, IExtractor<MajorityClass> extractor) {
        return super.selectAll(StatementsContainer.SQL_SELECT_ALL_CLASSES, extractor);
    }

    @Override
    public void deleteById(int id, String sql) {
        super.deleteById(id, StatementsContainer.SQL_DELETE_CLASS_BY_ID);
    }

    @Override
    public void update(MajorityClass majorityClass) {
        String sql = StatementsContainer.SQL_UPDATE_CLASS_BY_ID;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, majorityClass.getName());
            preparedStatement.setInt(2, majorityClass.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    @Override
    public void create(MajorityClass majorityClass) {
        String sql = StatementsContainer.SQL_INSERT_CLASS;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, majorityClass.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }
}