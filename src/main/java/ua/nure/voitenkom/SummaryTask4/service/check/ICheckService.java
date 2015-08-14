package ua.nure.voitenkom.SummaryTask4.service.check;

import ua.nure.voitenkom.SummaryTask4.db.entity.Car;
import ua.nure.voitenkom.SummaryTask4.db.entity.Check;

import java.util.List;

public interface ICheckService {

    List<Check> selectUnpayed();

    void insert(final Check check);

    void update(final Check check);

    void setPayed(final Check check);

    void updateSum(final Check check, int sum);

    int getSum(Car car, long days);

    int getSumWithDriver(Car car, long days);

    int selectLastInsertedId();

    Check selectById(int id);
}
