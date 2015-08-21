package ua.nure.voitenkom.SummaryTask4.db.repository.brand;

import ua.nure.voitenkom.SummaryTask4.db.entity.Brand;
import ua.nure.voitenkom.SummaryTask4.db.repository.IAbstractRepository;

public interface IBrandRepository extends IAbstractRepository<Brand> {

    Brand selectByName(String name);

}
