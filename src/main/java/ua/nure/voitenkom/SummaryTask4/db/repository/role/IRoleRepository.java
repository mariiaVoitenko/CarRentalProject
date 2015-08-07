package ua.nure.voitenkom.SummaryTask4.db.repository.role;

import ua.nure.voitenkom.SummaryTask4.db.entity.Role;
import ua.nure.voitenkom.SummaryTask4.db.repository.IAbstractRepository;

public interface IRoleRepository extends IAbstractRepository<Role>{

    int findByName(String name);

}
