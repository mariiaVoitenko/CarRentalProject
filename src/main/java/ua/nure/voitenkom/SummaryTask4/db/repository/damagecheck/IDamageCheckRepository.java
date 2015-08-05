package ua.nure.voitenkom.SummaryTask4.db.repository.damagecheck;

import ua.nure.voitenkom.SummaryTask4.db.entity.Damage;
import ua.nure.voitenkom.SummaryTask4.db.entity.DamageCheck;
import ua.nure.voitenkom.SummaryTask4.db.repository.IAbstractRepository;

/**
 * Created by Maria on 05.08.2015.
 */
public interface IDamageCheckRepository extends IAbstractRepository<DamageCheck> {

    DamageCheck findById(int id);

    void create(DamageCheck damageCheck);

    void update(DamageCheck damageCheck);
}
