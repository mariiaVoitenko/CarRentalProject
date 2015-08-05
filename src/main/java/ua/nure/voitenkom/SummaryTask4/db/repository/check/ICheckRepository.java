package ua.nure.voitenkom.SummaryTask4.db.repository.check;

import ua.nure.voitenkom.SummaryTask4.db.entity.Check;
import ua.nure.voitenkom.SummaryTask4.db.entity.Decline;
import ua.nure.voitenkom.SummaryTask4.db.repository.IAbstractRepository;

/**
 * Created by Maria on 05.08.2015.
 */
public interface ICheckRepository extends IAbstractRepository<Check> {

    Check findById(int id);

    void create(Check check);

    void update(Check check);
}

