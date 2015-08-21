package ua.nure.voitenkom.SummaryTask4.db.repository.color;

import ua.nure.voitenkom.SummaryTask4.db.entity.Color;
import ua.nure.voitenkom.SummaryTask4.db.repository.IAbstractRepository;

public interface IColorRepository extends IAbstractRepository<Color> {

    Color selectByName(String name);

}