package ua.nure.voitenkom.SummaryTask4.db.repository.role;

import ua.nure.voitenkom.SummaryTask4.db.StatementsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.Role;
import ua.nure.voitenkom.SummaryTask4.db.extractor.IExtractor;
import ua.nure.voitenkom.SummaryTask4.db.extractor.RoleExtractor;
import ua.nure.voitenkom.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.AbstractRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.IAbstractRepository;

import java.util.List;

/**
 * Created by Maria on 03.08.2015.
 */
public class RoleRepository extends AbstractRepository<Role> implements IRoleRepository{

    public RoleRepository(ConnectionHolder connectionHolder) {
        super(connectionHolder);
    }

    @Override
    public Role findById(int id) {
        return super.findById(id, StatementsContainer.SQL_SELECT_ROLE_BY_ID, new RoleExtractor());
    }

    @Override
    public int findByName(String name, String sql, IExtractor<Role> extractor) {
        return super.findByName(name, sql, extractor);
    }

    @Override
    public List<Role> findAll(String sql, IExtractor<Role> extractor) {
        return super.findAll(sql, extractor);
    }

    @Override
    public void deleteById(int id, String sql) {
        super.deleteById(id, sql);
    }
}
