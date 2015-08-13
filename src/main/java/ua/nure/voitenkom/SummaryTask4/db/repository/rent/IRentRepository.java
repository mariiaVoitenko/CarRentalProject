package ua.nure.voitenkom.SummaryTask4.db.repository.rent;

import ua.nure.voitenkom.SummaryTask4.db.entity.Decline;
import ua.nure.voitenkom.SummaryTask4.db.entity.Rent;
import ua.nure.voitenkom.SummaryTask4.db.repository.IAbstractRepository;

import java.sql.Timestamp;
import java.util.List;

public interface IRentRepository extends IAbstractRepository<Rent> {

    void insert(Rent rent);

    void update(Rent rent);

    List<Rent> selectAllForUser(int id);

    List<Rent> selectRentsForDates(Timestamp start, Timestamp end);

}