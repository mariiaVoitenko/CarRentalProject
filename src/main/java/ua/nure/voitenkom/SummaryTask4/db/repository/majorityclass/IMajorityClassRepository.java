package ua.nure.voitenkom.SummaryTask4.db.repository.majorityclass;

import ua.nure.voitenkom.SummaryTask4.db.entity.MajorityClass;
import ua.nure.voitenkom.SummaryTask4.db.repository.IAbstractRepository;

public interface IMajorityClassRepository extends IAbstractRepository<MajorityClass> {

    MajorityClass findByName(String name);

}

