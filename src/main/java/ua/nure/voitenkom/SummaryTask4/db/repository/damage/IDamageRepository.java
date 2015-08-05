package ua.nure.voitenkom.SummaryTask4.db.repository.damage;

import ua.nure.voitenkom.SummaryTask4.db.entity.Color;
import ua.nure.voitenkom.SummaryTask4.db.entity.Damage;
import ua.nure.voitenkom.SummaryTask4.db.repository.IAbstractRepository;

/**
 * Created by Maria on 05.08.2015.
 */
public interface IDamageRepository extends IAbstractRepository<Damage> {

    int findByName(String name);

    void insert(Damage damage);

    void update(Damage damage);

    void updateSum(Damage damage);
}
