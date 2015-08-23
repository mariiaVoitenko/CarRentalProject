package ua.nure.voitenkom.SummaryTask4.db.repository.user;

import ua.nure.voitenkom.SummaryTask4.db.entity.User;
import ua.nure.voitenkom.SummaryTask4.db.repository.IAbstractRepository;

import java.util.List;

public interface IUserRepository extends IAbstractRepository<User> {

    User selectByToken(String token);

    void insert(User user);

    void insertWithPhoto(User user);

    User findByLogin(String login);

    void makeBlocked(int id);

    void unblock(int id);

    void setRegisteredState(int id);

    boolean checkPassword(String login, String password);

    void changeRole(int roleId, int userId);

    List<User> selectByRoleId(int roleId);

    void updatePhoto(User user);

}
