package ua.nure.voitenkom.SummaryTask4.db.repository.status;

import ua.nure.voitenkom.SummaryTask4.db.entity.Status;
import ua.nure.voitenkom.SummaryTask4.db.repository.IAbstractRepository;

public interface IStatusRepository extends IAbstractRepository<Status> {

    Status findByName(String name);

}

