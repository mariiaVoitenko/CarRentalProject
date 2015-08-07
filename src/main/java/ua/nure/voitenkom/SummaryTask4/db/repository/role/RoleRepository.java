package ua.nure.voitenkom.SummaryTask4.db.repository.role;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.StatementsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.Role;
import ua.nure.voitenkom.SummaryTask4.db.entity.SimpleEntity;
import ua.nure.voitenkom.SummaryTask4.db.extractor.RoleExtractor;
import ua.nure.voitenkom.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.AbstractRepository;
import java.util.List;

public class RoleRepository extends AbstractRepository<Role> implements IRoleRepository {

    private static final Logger logger = LoggerFactory.getLogger(RoleRepository.class);

    public RoleRepository(ConnectionHolder connectionHolder) {
        super(connectionHolder);
    }

    @Override
    public Role selectById(int id) {
        return super.selectById(id, StatementsContainer.SQL_SELECT_ROLE_BY_ID, new RoleExtractor());
    }

    @Override
    public int findByName(String name) {
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
    public void update(SimpleEntity role) {
        super.update(role,StatementsContainer.SQL_UPDATE_ROLE_BY_ID);
    }

    @Override
    public void insert(SimpleEntity role) {
        super.insert(role,StatementsContainer.SQL_INSERT_ROLE);
    }
}
