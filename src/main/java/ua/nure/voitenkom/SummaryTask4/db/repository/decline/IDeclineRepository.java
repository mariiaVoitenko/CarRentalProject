package ua.nure.voitenkom.SummaryTask4.db.repository.decline;

import ua.nure.voitenkom.SummaryTask4.db.entity.Color;
import ua.nure.voitenkom.SummaryTask4.db.entity.Decline;
import ua.nure.voitenkom.SummaryTask4.db.repository.IAbstractRepository;

public interface IDeclineRepository extends IAbstractRepository<Decline> {

    int findByName(String name);

}
