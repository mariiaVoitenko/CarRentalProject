package ua.nure.voitenkom.SummaryTask4.db.repository.majorityclass;

import ua.nure.voitenkom.SummaryTask4.db.entity.Decline;
import ua.nure.voitenkom.SummaryTask4.db.entity.MajorityClass;
import ua.nure.voitenkom.SummaryTask4.db.repository.IAbstractRepository;

/**
 * Created by Maria on 05.08.2015.
 */
public interface IMajorityClassRepository extends IAbstractRepository<MajorityClass> {

    MajorityClass findById(int id);

    int findByName(String name);

    void create(MajorityClass majorityClass);

    void update(MajorityClass majorityClass);
}

