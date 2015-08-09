package ua.nure.voitenkom.SummaryTask4.service.role;

import ua.nure.voitenkom.SummaryTask4.db.entity.Role;

public interface IRoleService {

    Role selectByName(final String name);

}
