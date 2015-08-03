package ua.nure.voitenkom.SummaryTask4.db.repository.role;

import ua.nure.voitenkom.SummaryTask4.db.entity.Role;
import ua.nure.voitenkom.SummaryTask4.db.repository.IAbstractRepository;

/**
 * Created by Maria on 03.08.2015.
 */
public interface IRoleRepository extends IAbstractRepository<Role>{

    Role findById(int id);

}
