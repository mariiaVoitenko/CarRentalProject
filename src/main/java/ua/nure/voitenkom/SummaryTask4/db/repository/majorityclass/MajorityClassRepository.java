package ua.nure.voitenkom.SummaryTask4.db.repository.majorityclass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.StatementsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.MajorityClass;
import ua.nure.voitenkom.SummaryTask4.db.entity.SimpleEntity;
import ua.nure.voitenkom.SummaryTask4.db.extractor.IExtractor;
import ua.nure.voitenkom.SummaryTask4.db.extractor.MajorityClassExtractor;
import ua.nure.voitenkom.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.AbstractRepository;
import ua.nure.voitenkom.SummaryTask4.exception.DatabaseException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MajorityClassRepository extends AbstractRepository<MajorityClass> implements IMajorityClassRepository {

    private static final Logger logger = LoggerFactory.getLogger(MajorityClassRepository.class);

    public MajorityClassRepository(ConnectionHolder connectionHolder) {
        super(connectionHolder);
    }

    @Override
    public int findByName(String name) {
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
    public void insert(SimpleEntity entity) {
        super.insert(entity, StatementsContainer.SQL_INSERT_CLASS);
    }

    @Override
    public void update(SimpleEntity entity) {
        super.update(entity, StatementsContainer.SQL_UPDATE_CLASS_BY_ID);
    }

    @Override
    public void deleteById(int id) {
        super.deleteById(id, StatementsContainer.SQL_DELETE_CLASS_BY_ID);
    }
}