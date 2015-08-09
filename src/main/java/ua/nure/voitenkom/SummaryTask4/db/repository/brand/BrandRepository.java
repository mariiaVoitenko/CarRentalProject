package ua.nure.voitenkom.SummaryTask4.db.repository.brand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.StatementsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.Brand;
import ua.nure.voitenkom.SummaryTask4.db.entity.SimpleEntity;
import ua.nure.voitenkom.SummaryTask4.db.extractor.BrandExtractor;
import ua.nure.voitenkom.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.AbstractRepository;
import java.util.List;

public class BrandRepository extends AbstractRepository<Brand> implements IBrandRepository {

    private static final Logger logger = LoggerFactory.getLogger(BrandRepository.class);

    public BrandRepository(ConnectionHolder connectionHolder) {
        super(connectionHolder);
    }

    @Override
    public Brand selectById(int id) {
        return super.selectById(id, StatementsContainer.SQL_SELECT_BRAND_BY_ID, new BrandExtractor());
    }

    @Override
    public Brand selectByName(String name) {
        return super.selectByName(name, StatementsContainer.SQL_SELECT_BRAND_BY_NAME, new BrandExtractor());
    }

    @Override
    public List<Brand> selectAll() {
        return super.selectAll(StatementsContainer.SQL_SELECT_ALL_BRANDS, new BrandExtractor());
    }

    @Override
    public void deleteById(int id) {
        super.deleteById(id, StatementsContainer.SQL_DELETE_BRAND_BY_ID);
    }

    @Override
    public void update(SimpleEntity brand) {
        super.update(brand,StatementsContainer.SQL_UPDATE_BRAND_BY_ID);
    }

    @Override
    public void insert(SimpleEntity brand) {
       super.insert(brand,StatementsContainer.SQL_INSERT_BRAND);
    }
}