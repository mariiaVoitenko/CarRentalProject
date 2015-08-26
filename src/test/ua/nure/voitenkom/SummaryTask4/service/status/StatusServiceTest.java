package ua.nure.voitenkom.SummaryTask4.service.status;

import org.junit.Before;
import org.junit.Test;
import ua.nure.voitenkom.SummaryTask4.db.connection.IConnectionFactory;
import ua.nure.voitenkom.SummaryTask4.db.entity.Status;
import ua.nure.voitenkom.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.holder.IConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.status.IStatusRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.status.StatusRepository;
import ua.nure.voitenkom.SummaryTask4.db.transaction.ITransactionManager;
import ua.nure.voitenkom.SummaryTask4.db.transaction.TransactionManager;

import java.sql.Connection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StatusServiceTest {

    private static final Status BROKEN_STATUS = new Status("Broken");
    private IStatusService statusService;
    private Connection connection;
    private IStatusRepository statusRepository;

    @Before
    public void setUp() throws Exception {
        IConnectionHolder connHolder = new ConnectionHolder();
        IConnectionFactory connFactory = mock(IConnectionFactory.class);
        connection = mock(Connection.class);
        when(connFactory.getConnection()).thenReturn(connection);
        ITransactionManager txManager = new TransactionManager(connFactory, connHolder);
        statusRepository = mock(StatusRepository.class);
        statusService = new StatusService(txManager, statusRepository);
    }

    @Test
    public void testUserRole() throws Exception {
        when(statusRepository.findByName(BROKEN_STATUS.getName())).thenReturn(BROKEN_STATUS);
        assertEquals(BROKEN_STATUS, statusService.selectByName("Broken"));
        verify(connection).commit();
        verify(connection).close();
    }

}
