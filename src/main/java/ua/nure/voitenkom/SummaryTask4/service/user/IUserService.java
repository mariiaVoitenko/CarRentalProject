package ua.nure.voitenkom.SummaryTask4.service.user;

import ua.nure.voitenkom.SummaryTask4.db.entity.User;

import java.util.List;

public interface IUserService {

    User selectByToken(String token);

    void insert(User user);

    void insertWithPhoto(User user);

    User findByLogin(String login);

    void makeBlocked(int id);

    void unblock(int id);

    void setRegisteredState(int id);

    boolean checkPassword(String login, String password);

    void deleteUser(int id);

    List<User> getAll();

    User selectById(int id);

    void changeRole(int roleId, int userId);

    List<User> selectByRoleId(int roleId);

    void updatePhoto(User user);

}
