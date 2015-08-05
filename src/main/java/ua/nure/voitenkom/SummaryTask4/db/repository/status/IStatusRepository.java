package ua.nure.voitenkom.SummaryTask4.db.repository.status;

import ua.nure.voitenkom.SummaryTask4.db.entity.Decline;
import ua.nure.voitenkom.SummaryTask4.db.entity.Status;
import ua.nure.voitenkom.SummaryTask4.db.repository.IAbstractRepository;

/**
 * Created by Maria on 05.08.2015.
 */
public interface IStatusRepository extends IAbstractRepository<Status> {

    Status findById(int id);

    int findByName(String name);

    void create(Status status);

    void update(Status status);
}

