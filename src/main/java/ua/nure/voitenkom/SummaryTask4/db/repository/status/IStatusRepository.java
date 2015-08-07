package ua.nure.voitenkom.SummaryTask4.db.repository.status;

import ua.nure.voitenkom.SummaryTask4.db.entity.Decline;
import ua.nure.voitenkom.SummaryTask4.db.entity.Status;
import ua.nure.voitenkom.SummaryTask4.db.repository.IAbstractRepository;

public interface IStatusRepository extends IAbstractRepository<Status> {

    int findByName(String name);

}

