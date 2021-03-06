package ua.nure.voitenkom.SummaryTask4.service.damage;

import ua.nure.voitenkom.SummaryTask4.db.entity.Damage;

import java.util.List;

public interface IDamageService {

    Damage findByName(String name);

    Damage findById(int id);

    void insert(Damage damage);

    void update(Damage damage);

    List<Damage> getAll();

    int selectSumById(int id);

}
