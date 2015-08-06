package ua.nure.voitenkom.SummaryTask4.service.role;

import ua.nure.voitenkom.SummaryTask4.db.repository.role.IRoleRepository;
import ua.nure.voitenkom.SummaryTask4.db.transaction.ITransactionManager;
import ua.nure.voitenkom.SummaryTask4.db.transaction.TransactionManager;

/**
 * Created by Maria on 06.08.2015.
 */
public class RoleService implements  IRoleService{

    private final ITransactionManager transactionManager;
    private final IRoleRepository roleRepository;

    public RoleService(ITransactionManager transactionManager, IRoleRepository roleRepository) {
        this.transactionManager = transactionManager;
        this.roleRepository = roleRepository;
    }

}
