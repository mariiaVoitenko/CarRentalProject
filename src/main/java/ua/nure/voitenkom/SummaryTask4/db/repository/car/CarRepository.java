package ua.nure.voitenkom.SummaryTask4.db.repository.car;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.StatementsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.Car;
import ua.nure.voitenkom.SummaryTask4.db.extractor.CarExtractor;
import ua.nure.voitenkom.SummaryTask4.db.extractor.CarFormBeanExtractor;
import ua.nure.voitenkom.SummaryTask4.db.extractor.IExtractor;
import ua.nure.voitenkom.SummaryTask4.db.holder.IConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.AbstractRepository;
import ua.nure.voitenkom.SummaryTask4.exception.DatabaseException;
import ua.nure.voitenkom.SummaryTask4.formbean.CarFormBean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mariia Voitenko
 */
public class CarRepository extends AbstractRepository<Car> implements ICarRepository {

    private static final Logger logger = LoggerFactory.getLogger(CarRepository.class);

    public CarRepository(IConnectionHolder connectionHolder) {
        super(connectionHolder);
    }

    @Override
    public Car selectById(int id) {
        return super.selectById(id, StatementsContainer.SQL_SELECT_CAR_BY_ID, new CarExtractor());
    }

    @Override
    public List<Car> selectAll() {
        return super.selectAll(StatementsContainer.SQL_SELECT_ALL_CARS, new CarExtractor());
    }

    @Override
    public void deleteById(int id) {
        super.deleteById(id, StatementsContainer.SQL_DELETE_CAR_BY_ID);
    }


    @Override
    public List<Car> selectAll(String sql, IExtractor<Car> extractor) {
        return super.selectAll(StatementsContainer.SQL_SELECT_ALL_CARS, extractor);
    }

    @Override
    public void insert(Car car) {
        String sql = StatementsContainer.SQL_INSERT_CAR;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, car.getModel());
            preparedStatement.setInt(2, car.getPrice());
            preparedStatement.setInt(3, car.getDoorsCount());
            preparedStatement.setBoolean(4, car.isHasConditioner());
            preparedStatement.setInt(5, car.getBigLuggageCount());
            preparedStatement.setInt(6, car.getSmallLuggageCount());
            preparedStatement.setInt(7, car.getSitsCount());
            preparedStatement.setInt(8, car.getClassTypeId());
            preparedStatement.setInt(9, car.getColorId());
            preparedStatement.setInt(10, car.getStatusId());
            preparedStatement.setInt(11, car.getBrandId());
            preparedStatement.setString(12, car.getPhotoPath());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    @Override
    public void update(Car entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<CarFormBean> getFullCarInformation() {
        String sql = StatementsContainer.SQL_SELECT_ALL_CAR_INFORMATION;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            return extract(preparedStatement);
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    @Override
    public CarFormBean getFullCarInformationById(int id) {
        String sql = StatementsContainer.SQL_SELECT_ALL_CAR_INFORMATION_BY_ID;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            return extract(preparedStatement).get(0);
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    @Override
    public List<CarFormBean> getSortedCars(String sql) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            return extract(preparedStatement);
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    @Override
    public void updateCar(Car car) {
        String sql = StatementsContainer.SQL_UPDATE_CAR;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, car.getModel());
            preparedStatement.setInt(2, car.getPrice());
            preparedStatement.setInt(3, car.getDoorsCount());
            preparedStatement.setBoolean(4, car.isHasConditioner());
            preparedStatement.setInt(5, car.getBigLuggageCount());
            preparedStatement.setInt(6, car.getSmallLuggageCount());
            preparedStatement.setInt(7, car.getSitsCount());
            preparedStatement.setInt(8, car.getClassTypeId());
            preparedStatement.setInt(9, car.getColorId());
            preparedStatement.setInt(10, car.getStatusId());
            preparedStatement.setInt(11, car.getBrandId());
            preparedStatement.setString(12, car.getPhotoPath());
            preparedStatement.setInt(13, car.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    private List<CarFormBean> extract(PreparedStatement preparedStatement) throws SQLException {
        CarFormBeanExtractor extractor = new CarFormBeanExtractor();
        List<CarFormBean> carFormBeanList = new ArrayList<>();
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                carFormBeanList.add(extractor.extract(resultSet));
            }
        }
        return carFormBeanList;
    }

}