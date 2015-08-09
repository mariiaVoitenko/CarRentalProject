package ua.nure.voitenkom.SummaryTask4.db.repository.color;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.StatementsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.Brand;
import ua.nure.voitenkom.SummaryTask4.db.entity.Color;
import ua.nure.voitenkom.SummaryTask4.db.entity.SimpleEntity;
import ua.nure.voitenkom.SummaryTask4.db.extractor.BrandExtractor;
import ua.nure.voitenkom.SummaryTask4.db.extractor.ColorExtractor;
import ua.nure.voitenkom.SummaryTask4.db.extractor.IExtractor;
import ua.nure.voitenkom.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.AbstractRepository;
import ua.nure.voitenkom.SummaryTask4.exception.DatabaseException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ColorRepository extends AbstractRepository<Color> implements IColorRepository {

    private static final Logger logger = LoggerFactory.getLogger(ColorRepository.class);

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
    public void update(SimpleEntity brand) {
        super.update(brand,StatementsContainer.SQL_UPDATE_COLOR_BY_ID);
    }

    @Override
    public void insert(SimpleEntity brand) {
        super.insert(brand,StatementsContainer.SQL_INSERT_COLOR);
    }
}