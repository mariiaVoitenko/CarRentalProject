package ua.nure.voitenkom.SummaryTask4.db.repository.user;

import ua.nure.voitenkom.SummaryTask4.db.entity.User;
import ua.nure.voitenkom.SummaryTask4.db.repository.IAbstractRepository;

/**
 * Created by Maria on 03.08.2015.
 */
public interface IUserRepository extends IAbstractRepository<User> {

    void insert(User user);

    User findByLogin(String login);

    void makeBlocked(int id);

    void unblock(int id);

    void setRegisteredState(int id);
}
