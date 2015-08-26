package ua.nure.voitenkom.SummaryTask4.db.repository.brand;

import ua.nure.voitenkom.SummaryTask4.db.StatementsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.Brand;
import ua.nure.voitenkom.SummaryTask4.db.extractor.BrandExtractor;
import ua.nure.voitenkom.SummaryTask4.db.holder.IConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.AbstractSimpleEntityRepository;

import java.util.List;

/**
 * @author Mariia Voitenko
 */
public class BrandRepository extends AbstractSimpleEntityRepository<Brand> implements IBrandRepository {

    public BrandRepository(IConnectionHolder connectionHolder) {
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
    public void update(Brand brand) {
        super.update(brand, StatementsContainer.SQL_UPDATE_BRAND_BY_ID);
    }

    @Override
    public void insert(Brand brand) {
        super.insert(brand, StatementsContainer.SQL_INSERT_BRAND);
    }

}