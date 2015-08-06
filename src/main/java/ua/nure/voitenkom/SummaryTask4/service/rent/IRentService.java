package ua.nure.voitenkom.SummaryTask4.service.rent;

import ua.nure.voitenkom.SummaryTask4.db.entity.Rent;

import java.util.List;

/**
 * Created by Maria on 06.08.2015.
 */
public interface IRentService {

    void insert(Rent rent);

    void update(Rent rent);

    List<Rent> selectAllForUser(int id);

}
