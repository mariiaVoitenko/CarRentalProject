package ua.nure.voitenkom.SummaryTask4.db.repository.damagecheck;

import ua.nure.voitenkom.SummaryTask4.db.entity.Damage;
import ua.nure.voitenkom.SummaryTask4.db.entity.DamageCheck;
import ua.nure.voitenkom.SummaryTask4.db.repository.IAbstractRepository;

public interface IDamageCheckRepository extends IAbstractRepository<DamageCheck> {

    void create(DamageCheck damageCheck);

    void update(DamageCheck damageCheck);

}
