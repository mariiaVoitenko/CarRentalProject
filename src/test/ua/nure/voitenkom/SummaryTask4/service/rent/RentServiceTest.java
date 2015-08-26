package ua.nure.voitenkom.SummaryTask4.service.rent;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.nure.voitenkom.SummaryTask4.db.connection.IConnectionFactory;
import ua.nure.voitenkom.SummaryTask4.db.entity.Rent;
import ua.nure.voitenkom.SummaryTask4.db.entity.User;
import ua.nure.voitenkom.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.holder.IConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.rent.IRentRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.rent.RentRepository;
import ua.nure.voitenkom.SummaryTask4.db.transaction.ITransactionManager;
import ua.nure.voitenkom.SummaryTask4.db.transaction.TransactionManager;
import ua.nure.voitenkom.SummaryTask4.formbean.ApplicationFormBean;
import ua.nure.voitenkom.SummaryTask4.formbean.HistoryFormBean;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RentServiceTest {

    private Rent rent = mock(Rent.class);
    private IRentService rentService;
    private IRentRepository rentRepository;
    private Connection connection;

    @Before
    public void setUp() throws Exception {
        IConnectionHolder connHolder = new ConnectionHolder();
        IConnectionFactory connFactory = mock(IConnectionFactory.class);
        connection = mock(Connection.class);
        when(connFactory.getConnection()).thenReturn(connection);
        ITransactionManager txManager = new TransactionManager(connFactory, connHolder);
        rentRepository = mock(RentRepository.class);
        rentService = new RentService(txManager, rentRepository);
    }

    @Test
    public void testSelectById() throws Exception {
        int id = 1;
        when(rentRepository.selectById(id)).thenReturn(rent);
        assertEquals(rentService.selectById(id), rent);
        verify(connection).commit();
        verify(connection).close();
    }

    @Test
    public void testFindAllApplications() throws Exception {
        List<ApplicationFormBean> rents = mock(List.class);
        when(rentRepository.getApplications()).thenReturn(rents);
        Assert.assertEquals(rents, rentService.getApplications());
        verify(connection).commit();
        verify(connection).close();
    }

    @Test
    public void testFindAllReturned() throws Exception {
        List<ApplicationFormBean> rents = mock(List.class);
        when(rentRepository.getReturned()).thenReturn(rents);
        Assert.assertEquals(rents, rentService.getReturned());
        verify(connection).commit();
        verify(connection).close();
    }

    @Test
    public void testFindAllUserRents() throws Exception {
        List<HistoryFormBean> rents = mock(List.class);
        int id = 1;
        when(rentRepository.getUserRentsWithoutDeclines(id)).thenReturn(rents);
        Assert.assertEquals(rents, rentService.getUserRentsWithoutDeclines(id));
        verify(connection).commit();
        verify(connection).close();
    }

    @Test
    public void testFindAllUserRentsWithDeclines() throws Exception {
        List<HistoryFormBean> rents = mock(List.class);
        int id = 1;
        when(rentRepository.getUserRentsWithDeclines(id)).thenReturn(rents);
        Assert.assertEquals(rents, rentService.getUserRentsWithDeclines(id));
        verify(connection).commit();
        verify(connection).close();
    }

}
