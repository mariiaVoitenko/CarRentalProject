package ua.nure.voitenkom.SummaryTask4.db.repository.role;

import ua.nure.voitenkom.SummaryTask4.db.StatementsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.Role;
import ua.nure.voitenkom.SummaryTask4.db.extractor.RoleExtractor;
import ua.nure.voitenkom.SummaryTask4.db.holder.IConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.AbstractSimpleEntityRepository;

import java.util.List;

/**
 * @author Mariia Voitenko
 */
public class RoleRepository extends AbstractSimpleEntityRepository<Role> implements IRoleRepository {

    public RoleRepository(IConnectionHolder connectionHolder) {
        super(connectionHolder);
    }

    @Override
    public Role selectById(int id) {
        return super.selectById(id, StatementsContainer.SQL_SELECT_ROLE_BY_ID, new RoleExtractor());
    }

    @Override
    public Role findByName(String name) {
        return super.selectByName(name, StatementsContainer.SQL_SELECT_ROLE_BY_NAME, new RoleExtractor());
    }

    @Override
    public List<Role> selectAll() {
        return super.selectAll(StatementsContainer.SQL_SELECT_ALL_ROLES, new RoleExtractor());
    }

    @Override
    public void deleteById(int id) {
        super.deleteById(id, StatementsContainer.SQL_DELETE_ROLE_BY_ID);
    }

    @Override
    public void update(Role role) {
        super.update(role, StatementsContainer.SQL_UPDATE_ROLE_BY_ID);
    }

    @Override
    public void insert(Role role) {
        super.insert(role, StatementsContainer.SQL_INSERT_ROLE);
    }

}
