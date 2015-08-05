package ua.nure.voitenkom.SummaryTask4.db.repository.brand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.StatementsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.Brand;
import ua.nure.voitenkom.SummaryTask4.db.extractor.BrandExtractor;
import ua.nure.voitenkom.SummaryTask4.db.extractor.IExtractor;
import ua.nure.voitenkom.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.AbstractRepository;
import ua.nure.voitenkom.SummaryTask4.exception.DatabaseException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Maria on 04.08.2015.
 */
public class BrandRepository extends AbstractRepository<Brand> implements IBrandRepository {

    private static final Logger logger = LoggerFactory.getLogger(BrandRepository.class);

    public BrandRepository(ConnectionHolder connectionHolder) {
        super(connectionHolder);
    }

    @Override
    public Brand findById(int id) {
        return super.selectById(id, StatementsContainer.SQL_SELECT_BRAND_BY_ID, new BrandExtractor());
    }

    @Override
    public int findByName(String name) {
        return super.selectByName(name, StatementsContainer.SQL_SELECT_BRAND_BY_NAME, new BrandExtractor());
    }

    @Override
    public List<Brand> selectAll(String sql, IExtractor<Brand> extractor) {
        return super.selectAll(StatementsContainer.SQL_SELECT_ALL_BRANDS, extractor);
    }

    @Override
    public void deleteById(int id, String sql) {
        super.deleteById(id, StatementsContainer.SQL_DELETE_BRAND_BY_ID);
    }

    @Override
    public void update(Brand brand) {
        String sql = StatementsContainer.SQL_UPDATE_BRAND_BY_ID;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, brand.getName());
            preparedStatement.setInt(2, brand.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    @Override
    public void create(Brand brand) {
        String sql = StatementsContainer.SQL_INSERT_BRAND;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, brand.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }
}