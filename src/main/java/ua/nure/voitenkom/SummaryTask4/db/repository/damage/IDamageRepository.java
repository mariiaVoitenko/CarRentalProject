package ua.nure.voitenkom.SummaryTask4.db.repository.damage;

import ua.nure.voitenkom.SummaryTask4.db.entity.Color;
import ua.nure.voitenkom.SummaryTask4.db.entity.Damage;
import ua.nure.voitenkom.SummaryTask4.db.repository.IAbstractRepository;

/**
 * Created by Maria on 05.08.2015.
 */
public interface IDamageRepository extends IAbstractRepository<Damage> {

    Damage findById(int id);

    int findByName(String name);

    void create(Damage damage);

    void update(Damage damage);
}
