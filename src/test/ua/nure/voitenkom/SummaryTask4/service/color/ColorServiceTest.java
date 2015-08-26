package ua.nure.voitenkom.SummaryTask4.service.color;

import org.junit.Before;
import org.junit.Test;
import ua.nure.voitenkom.SummaryTask4.db.connection.IConnectionFactory;
import ua.nure.voitenkom.SummaryTask4.db.entity.Color;
import ua.nure.voitenkom.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.holder.IConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.color.ColorRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.color.IColorRepository;
import ua.nure.voitenkom.SummaryTask4.db.transaction.ITransactionManager;
import ua.nure.voitenkom.SummaryTask4.db.transaction.TransactionManager;

import java.sql.Connection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ColorServiceTest {

    private static final Color RED_COLOR = new Color("Red");
    private IColorService colorService;
    private Connection connection;
    private IColorRepository colorRepository;

    @Before
    public void setUp() throws Exception {
        IConnectionHolder connHolder = new ConnectionHolder();
        IConnectionFactory connFactory = mock(IConnectionFactory.class);
        connection = mock(Connection.class);
        when(connFactory.getConnection()).thenReturn(connection);
        ITransactionManager txManager = new TransactionManager(connFactory, connHolder);
        colorRepository = mock(ColorRepository.class);
        colorService = new ColorService(txManager, colorRepository);
    }

    @Test
    public void testNameGetting() throws Exception {
        when(colorRepository.selectByName(RED_COLOR.getName())).thenReturn(RED_COLOR);
        assertEquals(RED_COLOR, colorService.selectByName("Red"));
        verify(connection).commit();
        verify(connection).close();
    }

}
