package ua.nure.voitenkom.SummaryTask4.db.repository.color;

import ua.nure.voitenkom.SummaryTask4.db.entity.Brand;
import ua.nure.voitenkom.SummaryTask4.db.entity.Color;
import ua.nure.voitenkom.SummaryTask4.db.repository.IAbstractRepository;

/**
 * Created by Maria on 05.08.2015.
 */
public interface IColorRepository extends IAbstractRepository<Color> {

    Color findById(int id);

    int findByName(String name);

    void create(Color color);

    void update(Color color);
}