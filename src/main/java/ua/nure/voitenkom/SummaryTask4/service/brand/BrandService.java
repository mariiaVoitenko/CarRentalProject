package ua.nure.voitenkom.SummaryTask4.service.brand;

import ua.nure.voitenkom.SummaryTask4.db.entity.Brand;
import ua.nure.voitenkom.SummaryTask4.db.repository.brand.IBrandRepository;
import ua.nure.voitenkom.SummaryTask4.db.transaction.ITransactionManager;
import ua.nure.voitenkom.SummaryTask4.db.transaction.Operation;

import java.util.List;

/**
 * @author Mariia Voitenko
 */
public class BrandService implements IBrandService {

    private final ITransactionManager transactionManager;
    private final IBrandRepository brandRepository;

    public BrandService(ITransactionManager transactionManager, IBrandRepository brandRepository) {
        this.transactionManager = transactionManager;
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Brand> getAll() {
        return transactionManager.doInTransaction(new Operation<List<Brand>>() {
            @Override
            public List<Brand> doOperation() {
                return brandRepository.selectAll();
            }
        });
    }

    @Override
    public Brand selectById(final int id) {
        return transactionManager.doInTransaction(new Operation<Brand>() {
            @Override
            public Brand doOperation() {
                return brandRepository.selectById(id);
            }
        });
    }

    @Override
    public Brand selectByName(final String name) {
        return transactionManager.doInTransaction(new Operation<Brand>() {
            @Override
            public Brand doOperation() {
                return brandRepository.selectByName(name);
            }
        });
    }

}
