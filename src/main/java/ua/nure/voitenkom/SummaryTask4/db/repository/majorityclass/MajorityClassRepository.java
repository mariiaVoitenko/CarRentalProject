package ua.nure.voitenkom.SummaryTask4.db.repository.majorityclass;

import ua.nure.voitenkom.SummaryTask4.db.StatementsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.MajorityClass;
import ua.nure.voitenkom.SummaryTask4.db.extractor.MajorityClassExtractor;
import ua.nure.voitenkom.SummaryTask4.db.holder.IConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.AbstractSimpleEntityRepository;

import java.util.List;

/**
 * @author Mariia Voitenko
 */
public class MajorityClassRepository extends AbstractSimpleEntityRepository<MajorityClass> implements IMajorityClassRepository {

    public MajorityClassRepository(IConnectionHolder connectionHolder) {
        super(connectionHolder);
    }

    @Override
    public MajorityClass findByName(String name) {
        return super.selectByName(name, StatementsContainer.SQL_SELECT_CLASS_BY_NAME, new MajorityClassExtractor());
    }

    @Override
    public MajorityClass selectById(int id) {
        return super.selectById(id, StatementsContainer.SQL_SELECT_CLASS_BY_ID, new MajorityClassExtractor());
    }

    @Override
    public List<MajorityClass> selectAll() {
        return super.selectAll(StatementsContainer.SQL_SELECT_ALL_CLASSES, new MajorityClassExtractor());
    }

    @Override
    public void insert(MajorityClass entity) {
        super.insert(entity, StatementsContainer.SQL_INSERT_CLASS);
    }

    @Override
    public void update(MajorityClass entity) {
        super.update(entity, StatementsContainer.SQL_UPDATE_CLASS_BY_ID);
    }

    @Override
    public void deleteById(int id) {
        super.deleteById(id, StatementsContainer.SQL_DELETE_CLASS_BY_ID);
    }

}