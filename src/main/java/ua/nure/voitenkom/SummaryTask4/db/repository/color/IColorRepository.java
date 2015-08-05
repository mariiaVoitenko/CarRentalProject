package ua.nure.voitenkom.SummaryTask4.db.repository.color;

import ua.nure.voitenkom.SummaryTask4.db.entity.Brand;
import ua.nure.voitenkom.SummaryTask4.db.entity.Color;
import ua.nure.voitenkom.SummaryTask4.db.repository.IAbstractRepository;

/**
 * Created by Maria on 05.08.2015.
 */
public interface IColorRepository extends IAbstractRepository<Color> {

    int selectByName(String name);
}