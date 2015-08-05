package ua.nure.voitenkom.SummaryTask4.db.repository.check;

import ua.nure.voitenkom.SummaryTask4.db.entity.Check;
import ua.nure.voitenkom.SummaryTask4.db.entity.Decline;
import ua.nure.voitenkom.SummaryTask4.db.repository.IAbstractRepository;

import java.util.List;

/**
 * Created by Maria on 05.08.2015.
 */
public interface ICheckRepository extends IAbstractRepository<Check> {

    List<Check> selectUnpayed();

    void insert(Check check);

    void update(Check check);

    void setPayed(Check check);

    void updateSum(Check check, int sum);
}

