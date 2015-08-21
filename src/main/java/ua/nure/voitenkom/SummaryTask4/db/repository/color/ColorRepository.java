package ua.nure.voitenkom.SummaryTask4.db.repository.color;

import ua.nure.voitenkom.SummaryTask4.db.StatementsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.Color;
import ua.nure.voitenkom.SummaryTask4.db.extractor.ColorExtractor;
import ua.nure.voitenkom.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.AbstractSimpleEntityRepository;

import java.util.List;

public class ColorRepository extends AbstractSimpleEntityRepository<Color> implements IColorRepository {

    public ColorRepository(ConnectionHolder connectionHolder) {
        super(connectionHolder);
    }

    @Override
    public Color selectById(int id) {
        return super.selectById(id, StatementsContainer.SQL_SELECT_COLOR_BY_ID, new ColorExtractor());
    }

    @Override
    public Color selectByName(String name) {
        return super.selectByName(name, StatementsContainer.SQL_SELECT_COLOR_BY_NAME, new ColorExtractor());
    }

    @Override
    public List<Color> selectAll() {
        return super.selectAll(StatementsContainer.SQL_SELECT_ALL_COLORS, new ColorExtractor());
    }

    @Override
    public void deleteById(int id) {
        super.deleteById(id, StatementsContainer.SQL_DELETE_COLOR_BY_ID);
    }

    @Override
    public void update(Color color) {
        super.update(color, StatementsContainer.SQL_UPDATE_COLOR_BY_ID);
    }

    @Override
    public void insert(Color color) {
        super.insert(color, StatementsContainer.SQL_INSERT_COLOR);
    }

}