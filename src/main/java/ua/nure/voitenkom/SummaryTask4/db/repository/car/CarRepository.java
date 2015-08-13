package ua.nure.voitenkom.SummaryTask4.db.repository.car;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.FieldsContainer;
import ua.nure.voitenkom.SummaryTask4.db.StatementsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.Car;
import ua.nure.voitenkom.SummaryTask4.db.entity.SimpleEntity;
import ua.nure.voitenkom.SummaryTask4.db.extractor.CarExtractor;
import ua.nure.voitenkom.SummaryTask4.db.extractor.CarFormBeanExtractor;
import ua.nure.voitenkom.SummaryTask4.db.extractor.IExtractor;
import ua.nure.voitenkom.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.AbstractRepository;
import ua.nure.voitenkom.SummaryTask4.exception.DatabaseException;
import ua.nure.voitenkom.SummaryTask4.formbean.CarFormBean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarRepository extends AbstractRepository<Car> implements ICarRepository {

    private static final Logger logger = LoggerFactory.getLogger(CarRepository.class);
    public CarRepository(ConnectionHolder connectionHolder) {
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
    public void insert(SimpleEntity entity) {

    }

    @Override
    public void update(SimpleEntity entity) {

    }

    @Override
    public void deleteById(int id) {
        super.deleteById(id, StatementsContainer.SQL_DELETE_CAR_BY_ID);
    }

    @Override
    public String getBrandName(int id) {
        String sql = StatementsContainer.SQL_SELECT_CAR_BRAND_BY_ID;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<String> result = new ArrayList<>();
                while (resultSet.next()) {
                    String record = resultSet.getString(FieldsContainer.FIELD_NAME);
                    result.add(record);
                }
                return result.get(0);
            }
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    @Override
    public String getClassName(int id) {
        String sql = StatementsContainer.SQL_SELECT_CAR_CLASS_BY_ID;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<String> result = new ArrayList<>();
                while (resultSet.next()) {
                    String record = resultSet.getString(FieldsContainer.FIELD_NAME);
                    result.add(record);
                }
                return result.get(0);
            }
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    @Override
    public List<Car> selectAll(String sql, IExtractor<Car> extractor) {
        return super.selectAll(StatementsContainer.SQL_SELECT_ALL_CARS, extractor);
    }

    @Override
    public void updateStatus(Car car) {
        String sql = StatementsContainer.SQL_UPDATE_CAR_STATUS_BY_ID;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, car.getStatusId());
            preparedStatement.setInt(2, car.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    @Override
    public void updatePrice(Car car) {
        String sql = StatementsContainer.SQL_UPDATE_CAR_PRICE_BY_ID;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, car.getPrice());
            preparedStatement.setInt(2, car.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
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
    public List<CarFormBean> getFullCarInformation() {
        String sql = StatementsContainer.SQL_SELECT_ALL_CAR_INFORMATION;
        CarFormBeanExtractor extractor = new CarFormBeanExtractor();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<CarFormBean> result = new ArrayList<>();
                while (resultSet.next()) {
                    CarFormBean record = extractor.extract(resultSet);
                    result.add(record);
                }
                return result;
            }
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
}