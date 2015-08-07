package ua.nure.voitenkom.SummaryTask4.service.damage;

import ua.nure.voitenkom.SummaryTask4.db.entity.Damage;

public interface IDamageService {

    int findByName(String name);

    void insert(Damage damage);

    void update(Damage damage);

    void updateSum(Damage damage);

}
