package ua.nure.voitenkom.SummaryTask4.service.check;

import org.junit.Before;
import org.junit.Test;
import ua.nure.voitenkom.SummaryTask4.db.connection.IConnectionFactory;
import ua.nure.voitenkom.SummaryTask4.db.entity.Car;
import ua.nure.voitenkom.SummaryTask4.db.entity.Check;
import ua.nure.voitenkom.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.holder.IConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.check.CheckRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.check.ICheckRepository;
import ua.nure.voitenkom.SummaryTask4.db.transaction.ITransactionManager;
import ua.nure.voitenkom.SummaryTask4.db.transaction.TransactionManager;

import java.sql.Connection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CheckServiceTest {

    private Check check = mock(Check.class);
    private ICheckService checkService;
    private Connection connection;
    private ICheckRepository checkRepository;

    @Before
    public void setUp() throws Exception {
        IConnectionHolder connHolder = new ConnectionHolder();
        IConnectionFactory connFactory = mock(IConnectionFactory.class);
        connection = mock(Connection.class);
        when(connFactory.getConnection()).thenReturn(connection);
        ITransactionManager txManager = new TransactionManager(connFactory, connHolder);
        checkRepository = mock(CheckRepository.class);
        checkService = new CheckService(txManager, checkRepository);
    }

    @Test
    public void testSelectById() throws Exception {
        int id = 1;
        when(checkRepository.selectById(id)).thenReturn(check);
        assertEquals(check, checkService.selectById(id));
        verify(connection).commit();
        verify(connection).close();
    }

    @Test
    public void testSum() throws Exception {
        Car car = new Car(1, "model", 2000, 5, true, 1, 2, 3, 1, 2, 1, 2, "path");
        int days = 1;
        int sum = car.getPrice()*days;
        assertEquals(sum, checkService.getSum(car,days));
    }

    @Test
    public void testSumIncludingDriver() throws Exception {
        Car car = new Car(1, "model", 2000, 5, true, 1, 2, 3, 1, 2, 1, 2, "path");
        int days = 1;
        int sum = car.getPrice()*days+200;
        assertEquals(sum, checkService.getSumWithDriver(car,days));
    }

}
