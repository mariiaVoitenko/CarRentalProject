package ua.nure.voitenkom.SummaryTask4.service.brand;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.nure.voitenkom.SummaryTask4.db.connection.IConnectionFactory;
import ua.nure.voitenkom.SummaryTask4.db.entity.Brand;
import ua.nure.voitenkom.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.holder.IConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.brand.BrandRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.brand.IBrandRepository;
import ua.nure.voitenkom.SummaryTask4.db.transaction.ITransactionManager;
import ua.nure.voitenkom.SummaryTask4.db.transaction.TransactionManager;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BrandServiceTest {
    private static final Brand MAZDA_BRAND = new Brand("Mazda");
    private Brand brand = mock(Brand.class);
    private IBrandService brandService;
    private Connection connection;
    private IBrandRepository brandRepository;

    @Before
    public void setUp() throws Exception {
        IConnectionHolder connHolder = new ConnectionHolder();
        IConnectionFactory connFactory = mock(IConnectionFactory.class);
        connection = mock(Connection.class);
        when(connFactory.getConnection()).thenReturn(connection);
        ITransactionManager txManager = new TransactionManager(connFactory, connHolder);
        brandRepository = mock(BrandRepository.class);
        brandService = new BrandService(txManager, brandRepository);
    }

    @Test
    public void testNameGetting() throws Exception {
        when(brandRepository.selectByName(MAZDA_BRAND.getName())).thenReturn(MAZDA_BRAND);
        assertEquals(MAZDA_BRAND, brandService.selectByName("Mazda"));
        verify(connection).commit();
        verify(connection).close();
    }

    @Test
    public void testFindAll() throws Exception {
        List<Brand> brands = mock(List.class);
        when(brandRepository.selectAll()).thenReturn(brands);
        Assert.assertEquals(brands, brandService.getAll());
        verify(connection).commit();
        verify(connection).close();
    }

    @Test
    public void testSelectById() throws Exception {
        int id = 1;
        when(brandRepository.selectById(id)).thenReturn(brand);
        assertEquals(brand, brandService.selectById(id));
        verify(connection).commit();
        verify(connection).close();
    }

}
