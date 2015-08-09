package ua.nure.voitenkom.SummaryTask4.service.user;

import ua.nure.voitenkom.SummaryTask4.db.entity.User;
import ua.nure.voitenkom.SummaryTask4.db.repository.user.IUserRepository;
import ua.nure.voitenkom.SummaryTask4.db.transaction.ITransactionManager;
import ua.nure.voitenkom.SummaryTask4.db.transaction.Operation;
import ua.nure.voitenkom.SummaryTask4.db.transaction.TransactionManager;

public class UserService implements IUserService {

    private final ITransactionManager transactionManager;
    private final IUserRepository userRepository;

    public UserService(ITransactionManager transactionManager, IUserRepository userRepository) {
        this.transactionManager = transactionManager;
        this.userRepository = userRepository;
    }

    @Override
    public void insert(final User user) {
        transactionManager.doInTransaction(new Operation<Void>() {
            @Override
            public Void doOperation() {
                userRepository.insert(user);
                return null;
            }
        });
    }

    @Override
    public User findByLogin(final String login) {
        return transactionManager.doInTransaction(new Operation<User>() {
            @Override
            public User doOperation() {
                return userRepository.findByLogin(login);
            }
        });
    }

    @Override
    public void makeBlocked(final int id) {
        transactionManager.doInTransaction(new Operation<Void>() {
            @Override
            public Void doOperation() {
                userRepository.makeBlocked(id);
                return null;
            }
        });
    }

    @Override
    public void unblock(final int id) {
        transactionManager.doInTransaction(new Operation<Void>() {
            @Override
            public Void doOperation() {
                userRepository.unblock(id);
                return null;
            }
        });
    }

    @Override
    public void setRegisteredState(final int id) {
        transactionManager.doInTransaction(new Operation<Void>() {
            @Override
            public Void doOperation() {
                userRepository.setRegisteredState(id);
                return null;
            }
        });
    }

    @Override
    public boolean checkPassword(final String login, final String password) {
        return transactionManager.doInTransaction(new Operation<Boolean>() {
            @Override
            public Boolean doOperation() {
                return userRepository.checkPassword(login, password);
            }
        });
    }
}
