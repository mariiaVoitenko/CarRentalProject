package ua.nure.voitenkom.SummaryTask4.db.repository.color;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.StatementsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.Color;
import ua.nure.voitenkom.SummaryTask4.db.extractor.ColorExtractor;
import ua.nure.voitenkom.SummaryTask4.db.extractor.IExtractor;
import ua.nure.voitenkom.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.AbstractRepository;
import ua.nure.voitenkom.SummaryTask4.exception.DatabaseException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Maria on 05.08.2015.
 */
public class ColorRepository extends AbstractRepository<Color> implements IColorRepository {

    private static final Logger logger = LoggerFactory.getLogger(ColorRepository.class);

    public ColorRepository(ConnectionHolder connectionHolder) {
        super(connectionHolder);
    }

    @Override
    public Color findById(int id) {
        return super.selectById(id, StatementsContainer.SQL_SELECT_COLOR_BY_ID, new ColorExtractor());
    }

    @Override
    public int findByName(String name) {
        return super.selectByName(name, StatementsContainer.SQL_SELECT_COLOR_BY_NAME, new ColorExtractor());
    }

    @Override
    public List<Color> selectAll(String sql, IExtractor<Color> extractor) {
        return super.selectAll(StatementsContainer.SQL_SELECT_ALL_COLORS, extractor);
    }

    @Override
    public void deleteById(int id, String sql) {
        super.deleteById(id, StatementsContainer.SQL_DELETE_COLOR_BY_ID);
    }

    @Override
    public void update(Color color) {
        String sql = StatementsContainer.SQL_UPDATE_COLOR_BY_ID;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, color.getName());
            preparedStatement.setInt(2, color.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    @Override
    public void create(Color color) {
        String sql = StatementsContainer.SQL_INSERT_COLOR;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, color.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }
}