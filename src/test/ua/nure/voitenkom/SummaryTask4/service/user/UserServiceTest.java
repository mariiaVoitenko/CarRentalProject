package ua.nure.voitenkom.SummaryTask4.service.user;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.nure.voitenkom.SummaryTask4.db.connection.IConnectionFactory;
import ua.nure.voitenkom.SummaryTask4.db.entity.Car;
import ua.nure.voitenkom.SummaryTask4.db.entity.User;
import ua.nure.voitenkom.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.holder.IConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.user.IUserRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.user.UserRepository;
import ua.nure.voitenkom.SummaryTask4.db.transaction.ITransactionManager;
import ua.nure.voitenkom.SummaryTask4.db.transaction.TransactionManager;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private User user = mock(User.class);
    private IUserService userService;
    private IUserRepository userRepository;
    private Connection connection;

    @Before
    public void setUp() throws Exception {
        IConnectionHolder connHolder = new ConnectionHolder();
        IConnectionFactory connFactory = mock(IConnectionFactory.class);
        connection = mock(Connection.class);
        when(connFactory.getConnection()).thenReturn(connection);
        ITransactionManager txManager = new TransactionManager(connFactory, connHolder);
        userRepository = mock(UserRepository.class);
        userService = new UserService(txManager, userRepository);
    }

    @Test
    public void testLoginExists() throws Exception {
        String checkedLogin = "admin@gmail.com";
        when(userRepository.findByLogin(checkedLogin)).thenReturn(user);
        assertEquals(userService.findByLogin(checkedLogin), user);
        verify(connection).commit();
        verify(connection).close();
    }

    @Test
    public void testLoginNotExists() throws Exception {
        String checkedLogin = "login";
        when(userRepository.findByLogin(checkedLogin)).thenReturn(null);
        assertEquals(userService.findByLogin(checkedLogin), null);
        verify(connection).commit();
        verify(connection).close();
    }

    @Test
    public void testUserToken() throws Exception {
        String token = "a0161212-7a2c-4259-9bc3-8b6bc27be5f4";
        when(userRepository.selectByToken(token)).thenReturn(user);
        assertEquals(userService.selectByToken(token), user);
        verify(connection).commit();
        verify(connection).close();
    }

    @Test
    public void testWrongUserToken() throws Exception {
        String token = "12345678";
        when(userRepository.selectByToken(token)).thenReturn(null);
        assertEquals(userService.selectByToken(token), null);
        verify(connection).commit();
        verify(connection).close();
    }

    @Test
    public void testLoginAndPassword() throws Exception {
        String login = "login";
        String password = "password";
        boolean correct = false;
        when(userRepository.checkPassword(login, password)).thenReturn(correct);
        assertEquals(userService.checkPassword(login, password), correct);
        verify(connection).commit();
        verify(connection).close();
    }

    @Test
    public void testWrongLoginAndPassword() throws Exception {
        String login = "login";
        String password = "password";
        boolean correct = false;
        when(userRepository.checkPassword(login, password)).thenReturn(false);
        assertEquals(userService.checkPassword(login, password), correct);
        verify(connection).commit();
        verify(connection).close();
    }

    @Test
    public void testSelectById() throws Exception {
        int id = 1;
        when(userRepository.selectById(id)).thenReturn(user);
        assertEquals(userService.selectById(id), user);
        verify(connection).commit();
        verify(connection).close();
    }

    @Test
    public void testSelectByRoleId() throws Exception{
        int roleId = 1;
        List<User> users = mock(List.class);
        when(userRepository.selectByRoleId(roleId)).thenReturn(users);
        Assert.assertEquals(users, userService.selectByRoleId(roleId));
        verify(connection).commit();
        verify(connection).close();
    }

    @Test
    public void testFindAll() throws Exception {
        List<User> users = mock(List.class);
        when(userRepository.selectAll()).thenReturn(users);
        Assert.assertEquals(users, userService.getAll());
        verify(connection).commit();
        verify(connection).close();
    }

}
