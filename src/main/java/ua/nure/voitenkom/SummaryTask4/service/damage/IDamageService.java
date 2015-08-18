package ua.nure.voitenkom.SummaryTask4.service.damage;

import ua.nure.voitenkom.SummaryTask4.db.entity.Damage;

import java.util.List;

public interface IDamageService {

    Damage findByName(String name);

    void insert(Damage damage);

    void update(Damage damage);

    void updateSum(Damage damage);

    List<Damage> getAll();

}
