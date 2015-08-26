package ua.nure.voitenkom.SummaryTask4.db.repository.check;

import ua.nure.voitenkom.SummaryTask4.db.entity.Check;
import ua.nure.voitenkom.SummaryTask4.db.repository.IAbstractRepository;

public interface ICheckRepository extends IAbstractRepository<Check> {

    void updateSum(Check check, int sum);

    int selectLastInsertedId();

}

