package ua.nure.voitenkom.SummaryTask4.service.color;

import ua.nure.voitenkom.SummaryTask4.db.entity.Color;
import ua.nure.voitenkom.SummaryTask4.db.repository.color.IColorRepository;
import ua.nure.voitenkom.SummaryTask4.db.transaction.ITransactionManager;
import ua.nure.voitenkom.SummaryTask4.db.transaction.Operation;

import java.util.List;

/**
 * @author Mariia Voitenko
 */
public class ColorService implements IColorService {

    private final ITransactionManager transactionManager;
    private final IColorRepository colorRepository;

    public ColorService(ITransactionManager transactionManager, IColorRepository colorRepository) {
        this.transactionManager = transactionManager;
        this.colorRepository = colorRepository;
    }

    @Override
    public List<Color> getAll() {
        return transactionManager.doInTransaction(new Operation<List<Color>>() {
            @Override
            public List<Color> doOperation() {
                return colorRepository.selectAll();
            }
        });
    }

    @Override
    public Color selectById(final int id) {
        return transactionManager.doInTransaction(new Operation<Color>() {
            @Override
            public Color doOperation() {
                return colorRepository.selectById(id);
            }
        });
    }

    @Override
    public Color selectByName(final String name) {
        return transactionManager.doInTransaction(new Operation<Color>() {
            @Override
            public Color doOperation() {
                return colorRepository.selectByName(name);
            }
        });
    }
}
