package ua.nure.voitenkom.SummaryTask4.service.role;

import org.junit.Before;
import org.junit.Test;
import ua.nure.voitenkom.SummaryTask4.db.connection.IConnectionFactory;
import ua.nure.voitenkom.SummaryTask4.db.entity.Role;
import ua.nure.voitenkom.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.holder.IConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.role.IRoleRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.role.RoleRepository;
import ua.nure.voitenkom.SummaryTask4.db.transaction.ITransactionManager;
import ua.nure.voitenkom.SummaryTask4.db.transaction.TransactionManager;

import java.sql.Connection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RoleServiceTest {

    private static final Role USER_ROLE = new Role("User");
    private IRoleService roleService;
    private Connection connection;
    private IRoleRepository roleRepository;

    @Before
    public void setUp() throws Exception {
        IConnectionHolder connHolder = new ConnectionHolder();
        IConnectionFactory connFactory = mock(IConnectionFactory.class);
        connection = mock(Connection.class);
        when(connFactory.getConnection()).thenReturn(connection);
        ITransactionManager txManager = new TransactionManager(connFactory, connHolder);
        roleRepository = mock(RoleRepository.class);
        roleService = new RoleService(txManager, roleRepository);
    }

    @Test
    public void testUserRole() throws Exception {
        when(roleRepository.findByName(USER_ROLE.getName())).thenReturn(USER_ROLE);
        assertEquals(USER_ROLE, roleService.selectByName("User"));
        verify(connection).commit();
        verify(connection).close();
    }

}
