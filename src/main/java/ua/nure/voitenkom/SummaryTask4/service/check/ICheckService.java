package ua.nure.voitenkom.SummaryTask4.service.check;

import ua.nure.voitenkom.SummaryTask4.db.entity.Check;

import java.util.List;

public interface ICheckService {

    List<Check> selectUnpayed();

    void insert(final Check check);

    void update(final Check check);

    void setPayed(final Check check);

    void updateSum(final Check check, int sum);
}
