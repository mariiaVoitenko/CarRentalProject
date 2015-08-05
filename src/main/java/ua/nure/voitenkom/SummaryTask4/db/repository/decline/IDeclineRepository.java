package ua.nure.voitenkom.SummaryTask4.db.repository.decline;

import ua.nure.voitenkom.SummaryTask4.db.entity.Color;
import ua.nure.voitenkom.SummaryTask4.db.entity.Decline;
import ua.nure.voitenkom.SummaryTask4.db.repository.IAbstractRepository;

/**
 * Created by Maria on 05.08.2015.
 */
public interface IDeclineRepository extends IAbstractRepository<Decline> {

    Decline findById(int id);

    int findByName(String name);

    void create(Decline decline);

    void update(Decline decline);
}
