package ua.nure.voitenkom.SummaryTask4.service.damage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.nure.voitenkom.SummaryTask4.db.connection.IConnectionFactory;
import ua.nure.voitenkom.SummaryTask4.db.entity.Color;
import ua.nure.voitenkom.SummaryTask4.db.entity.Damage;
import ua.nure.voitenkom.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.holder.IConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.check.CheckRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.damage.DamageRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.damage.IDamageRepository;
import ua.nure.voitenkom.SummaryTask4.db.transaction.ITransactionManager;
import ua.nure.voitenkom.SummaryTask4.db.transaction.TransactionManager;
import ua.nure.voitenkom.SummaryTask4.service.check.CheckService;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DamageServiceTest {

    private Damage damage = mock(Damage.class);
    private static final Damage SCRATCH_DAMAGE = new Damage("Scratch", 400);
    private IDamageService damageService;
    private Connection connection;
    private IDamageRepository damageRepository;

    @Before
    public void setUp() throws Exception {
        IConnectionHolder connHolder = new ConnectionHolder();
        IConnectionFactory connFactory = mock(IConnectionFactory.class);
        connection = mock(Connection.class);
        when(connFactory.getConnection()).thenReturn(connection);
        ITransactionManager txManager = new TransactionManager(connFactory, connHolder);
        damageRepository = mock(DamageRepository.class);
        damageService = new DamageService(txManager, damageRepository);
    }

    @Test
    public void testNameGetting() throws Exception {
        when(damageRepository.findByName(SCRATCH_DAMAGE.getName())).thenReturn(SCRATCH_DAMAGE);
        assertEquals(SCRATCH_DAMAGE, damageService.findByName("Scratch"));
        verify(connection).commit();
        verify(connection).close();
    }

    @Test
    public void testSelectById() throws Exception {
        int id = 1;
        when(damageRepository.selectById(id)).thenReturn(damage);
        assertEquals(damage, damageService.findById(id));
        verify(connection).commit();
        verify(connection).close();
    }

    @Test
    public void testFindAll() throws Exception {
        List<Damage> damageList = mock(List.class);
        when(damageRepository.selectAll()).thenReturn(damageList);
        Assert.assertEquals(damageList, damageService.getAll());
        verify(connection).commit();
        verify(connection).close();
    }

    @Test
    public void testSelectSum() throws Exception {
        int id = 1;
        int sum = 0;
        when(damageRepository.selectSumById(id)).thenReturn(sum);
        Assert.assertEquals(sum, damageService.selectSumById(id));
        verify(connection).commit();
        verify(connection).close();
    }

}
