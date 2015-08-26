package ua.nure.voitenkom.SummaryTask4.service.car;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.nure.voitenkom.SummaryTask4.db.connection.IConnectionFactory;
import ua.nure.voitenkom.SummaryTask4.db.converter.ISQLBuilder;
import ua.nure.voitenkom.SummaryTask4.db.converter.SQLBuilder;
import ua.nure.voitenkom.SummaryTask4.db.entity.Brand;
import ua.nure.voitenkom.SummaryTask4.db.entity.Car;
import ua.nure.voitenkom.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.holder.IConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.brand.BrandRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.car.CarRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.car.ICarRepository;
import ua.nure.voitenkom.SummaryTask4.db.transaction.ITransactionManager;
import ua.nure.voitenkom.SummaryTask4.db.transaction.TransactionManager;
import ua.nure.voitenkom.SummaryTask4.service.brand.BrandService;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CarServiceTest {

    private Car car = mock(Car.class);
    private ICarService carService;
    private Connection connection;
    private ICarRepository carRepository;
    private ISQLBuilder sqlBuilder;

    @Before
    public void setUp() throws Exception {
        IConnectionHolder connHolder = new ConnectionHolder();
        IConnectionFactory connFactory = mock(IConnectionFactory.class);
        connection = mock(Connection.class);
        when(connFactory.getConnection()).thenReturn(connection);
        ITransactionManager txManager = new TransactionManager(connFactory, connHolder);
        carRepository = mock(CarRepository.class);
        sqlBuilder = mock(SQLBuilder.class);
        carService = new CarService(txManager, carRepository, sqlBuilder);
    }

    @Test
    public void testFindAll() throws Exception {
        List<Car> cars = mock(List.class);
        when(carRepository.selectAll()).thenReturn(cars);
        Assert.assertEquals(cars, carService.getAll());
        verify(connection).commit();
        verify(connection).close();
    }

    @Test
    public void testSelectById() throws Exception {
        int id = 1;
        when(carRepository.selectById(id)).thenReturn(car);
        assertEquals(carService.getById(id), car);
        verify(connection).commit();
        verify(connection).close();
    }

}
