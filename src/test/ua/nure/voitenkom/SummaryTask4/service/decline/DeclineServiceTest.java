package ua.nure.voitenkom.SummaryTask4.service.decline;

import org.junit.Before;
import org.junit.Test;
import ua.nure.voitenkom.SummaryTask4.db.connection.IConnectionFactory;
import ua.nure.voitenkom.SummaryTask4.db.entity.Decline;
import ua.nure.voitenkom.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.holder.IConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.decline.DeclineRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.decline.IDeclineRepository;
import ua.nure.voitenkom.SummaryTask4.db.transaction.ITransactionManager;
import ua.nure.voitenkom.SummaryTask4.db.transaction.TransactionManager;

import java.sql.Connection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DeclineServiceTest {

    private static final Decline BAD_REPUTATION = new Decline("Bad reputation");
    private IDeclineService declineService;
    private Connection connection;
    private IDeclineRepository declineRepository;

    @Before
    public void setUp() throws Exception {
        IConnectionHolder connHolder = new ConnectionHolder();
        IConnectionFactory connFactory = mock(IConnectionFactory.class);
        connection = mock(Connection.class);
        when(connFactory.getConnection()).thenReturn(connection);
        ITransactionManager txManager = new TransactionManager(connFactory, connHolder);
        declineRepository = mock(DeclineRepository.class);
        declineService = new DeclineService(txManager, declineRepository);
    }

    @Test
    public void testNameGetting() throws Exception {
        when(declineRepository.findByName(BAD_REPUTATION.getName())).thenReturn(BAD_REPUTATION);
        assertEquals(BAD_REPUTATION, declineService.selectByName("Bad reputation"));
        verify(connection).commit();
        verify(connection).close();
    }

}
