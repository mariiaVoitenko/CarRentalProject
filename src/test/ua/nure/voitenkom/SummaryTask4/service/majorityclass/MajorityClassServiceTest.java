package ua.nure.voitenkom.SummaryTask4.service.majorityclass;

import org.junit.Before;
import org.junit.Test;
import ua.nure.voitenkom.SummaryTask4.db.connection.IConnectionFactory;
import ua.nure.voitenkom.SummaryTask4.db.entity.MajorityClass;
import ua.nure.voitenkom.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.holder.IConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.majorityclass.IMajorityClassRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.majorityclass.MajorityClassRepository;
import ua.nure.voitenkom.SummaryTask4.db.transaction.ITransactionManager;
import ua.nure.voitenkom.SummaryTask4.db.transaction.TransactionManager;

import java.sql.Connection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MajorityClassServiceTest {

    private static final MajorityClass ECONOMY_CLASS = new MajorityClass("Economy");
    private IMajorityClassService majorityClassService;
    private Connection connection;
    private IMajorityClassRepository majorityClassRepository;

    @Before
    public void setUp() throws Exception {
        IConnectionHolder connHolder = new ConnectionHolder();
        IConnectionFactory connFactory = mock(IConnectionFactory.class);
        connection = mock(Connection.class);
        when(connFactory.getConnection()).thenReturn(connection);
        ITransactionManager txManager = new TransactionManager(connFactory, connHolder);
        majorityClassRepository = mock(MajorityClassRepository.class);
        majorityClassService = new MajorityClassService(txManager, majorityClassRepository);
    }

    @Test
    public void testNameGetting() throws Exception {
        when(majorityClassRepository.findByName(ECONOMY_CLASS.getName())).thenReturn(ECONOMY_CLASS);
        assertEquals(ECONOMY_CLASS, majorityClassService.selectByName("Economy"));
        verify(connection).commit();
        verify(connection).close();
    }

}
