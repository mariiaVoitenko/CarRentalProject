package ua.nure.voitenkom.SummaryTask4.db.repository.check;

import ua.nure.voitenkom.SummaryTask4.db.entity.Check;
import ua.nure.voitenkom.SummaryTask4.db.repository.IAbstractRepository;

import java.util.List;

public interface ICheckRepository extends IAbstractRepository<Check> {

    List<Check> selectUnpayed();

    void setPayed(Check check);

    void updateSum(Check check, int sum);

    int selectLastInsertedId();

}

