package ua.nure.voitenkom.SummaryTask4.service.user;

import ua.nure.voitenkom.SummaryTask4.db.entity.User;
import ua.nure.voitenkom.SummaryTask4.db.repository.user.IUserRepository;
import ua.nure.voitenkom.SummaryTask4.db.transaction.ITransactionManager;
import ua.nure.voitenkom.SummaryTask4.db.transaction.Operation;
import java.util.List;

public class UserService implements IUserService {

    private final ITransactionManager transactionManager;
    private final IUserRepository userRepository;

    public UserService(ITransactionManager transactionManager, IUserRepository userRepository) {
        this.transactionManager = transactionManager;
        this.userRepository = userRepository;
    }

    @Override
    public User selectByToken(final String token) {
        return transactionManager.doInTransaction(new Operation<User>() {
            @Override
            public User doOperation() {
                return userRepository.selectByToken(token);
            }
        });
    }

    @Override
    public User selectById(final int id) {
        return transactionManager.doInTransaction(new Operation<User>() {
            @Override
            public User doOperation() {
                return userRepository.selectById(id);
            }
        });
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
    public void insertWithPhoto(final User user) {
        transactionManager.doInTransaction(new Operation<Void>() {
            @Override
            public Void doOperation() {
                userRepository.insertWithPhoto(user);
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
    public void changeRole(final int roleId, final int userId) {
        transactionManager.doInTransaction(new Operation<Void>() {
            @Override
            public Void doOperation() {
                userRepository.changeRole(roleId,userId);
                return null;
            }
        });
    }

    @Override
    public List<User> selectByRoleId(final int roleId) {
        return transactionManager.doInTransaction(new Operation<List<User>>() {
            @Override
            public List<User>  doOperation() {
                return userRepository.selectByRoleId(roleId);
            }
        });
    }

    @Override
    public void updatePhoto(final User user) {
        transactionManager.doInTransaction(new Operation<Void>() {
            @Override
            public Void doOperation() {
                userRepository.updatePhoto(user);
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

    @Override
    public void deleteUser(final int id) {
        transactionManager.doInTransaction(new Operation<Void>() {
            @Override
            public Void doOperation() {
                userRepository.deleteById(id);
                return null;
            }
        });
    }

    @Override
    public List<User> getAll() {
        return transactionManager.doInTransaction(new Operation<List<User>>() {
            @Override
            public List<User> doOperation() {
                return userRepository.selectAll();
            }
        });
    }
}
