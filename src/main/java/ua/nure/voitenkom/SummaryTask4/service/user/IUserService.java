package ua.nure.voitenkom.SummaryTask4.service.user;

import ua.nure.voitenkom.SummaryTask4.db.entity.User;

/**
 * Created by Maria on 05.08.2015.
 */
public interface IUserService {

    void insert(User user);

    User findByLogin(String login);

    void makeBlocked(int id);

    void unblock(int id);

    void setRegisteredState(int id);

}
