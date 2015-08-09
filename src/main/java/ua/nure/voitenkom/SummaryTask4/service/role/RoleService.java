package ua.nure.voitenkom.SummaryTask4.service.role;

import ua.nure.voitenkom.SummaryTask4.db.entity.Role;
import ua.nure.voitenkom.SummaryTask4.db.repository.role.IRoleRepository;
import ua.nure.voitenkom.SummaryTask4.db.transaction.ITransactionManager;
import ua.nure.voitenkom.SummaryTask4.db.transaction.Operation;
import ua.nure.voitenkom.SummaryTask4.db.transaction.TransactionManager;

public class RoleService implements  IRoleService{

    private final ITransactionManager transactionManager;
    private final IRoleRepository roleRepository;

    public RoleService(ITransactionManager transactionManager, IRoleRepository roleRepository) {
        this.transactionManager = transactionManager;
        this.roleRepository = roleRepository;
    }

    @Override
    public Role selectByName(final String name) {
        return transactionManager.doInTransaction(new Operation<Role>() {
            @Override
            public Role doOperation() {
                return roleRepository.findByName(name);
            }
        });
    }
}
