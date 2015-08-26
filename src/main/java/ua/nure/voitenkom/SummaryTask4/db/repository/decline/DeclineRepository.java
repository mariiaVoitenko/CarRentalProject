package ua.nure.voitenkom.SummaryTask4.db.repository.decline;

import ua.nure.voitenkom.SummaryTask4.db.StatementsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.Decline;
import ua.nure.voitenkom.SummaryTask4.db.extractor.DeclineExtractor;
import ua.nure.voitenkom.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.holder.IConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.AbstractSimpleEntityRepository;

import java.util.List;

public class DeclineRepository extends AbstractSimpleEntityRepository<Decline> implements IDeclineRepository {

    public DeclineRepository(IConnectionHolder connectionHolder) {
        super(connectionHolder);
    }

    @Override
    public Decline findByName(String name) {
        return super.selectByName(name, StatementsContainer.SQL_SELECT_DECLINE_BY_NAME, new DeclineExtractor());
    }

    @Override
    public Decline selectById(int id) {
        return super.selectById(id, StatementsContainer.SQL_SELECT_DECLINE_BY_ID, new DeclineExtractor());
    }

    @Override
    public List<Decline> selectAll() {
        return super.selectAll(StatementsContainer.SQL_SELECT_ALL_DECLINES, new DeclineExtractor());
    }

    @Override
    public void insert(Decline entity) {
        super.insert(entity, StatementsContainer.SQL_INSERT_DECLINE);
    }

    @Override
    public void update(Decline entity) {
        super.insert(entity, StatementsContainer.SQL_UPDATE_DECLINE_BY_ID);
    }

    @Override
    public void deleteById(int id) {
        super.deleteById(id, StatementsContainer.SQL_DELETE_DECLINE_BY_ID);
    }

}
