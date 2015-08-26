package ua.nure.voitenkom.SummaryTask4.db.repository.damage;

import ua.nure.voitenkom.SummaryTask4.db.entity.Damage;
import ua.nure.voitenkom.SummaryTask4.db.repository.IAbstractRepository;

public interface IDamageRepository extends IAbstractRepository<Damage> {

    Damage findByName(String name);

    void insert(Damage damage);

    void update(Damage damage);

    int selectSumById(int id);

}
