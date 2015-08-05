package ua.nure.voitenkom.SummaryTask4.db.repository.brand;

import ua.nure.voitenkom.SummaryTask4.db.entity.Brand;
import ua.nure.voitenkom.SummaryTask4.db.entity.Role;
import ua.nure.voitenkom.SummaryTask4.db.repository.IAbstractRepository;

/**
 * Created by Maria on 04.08.2015.
 */
public interface IBrandRepository extends IAbstractRepository<Brand> {

    int selectByName(String name);

}
